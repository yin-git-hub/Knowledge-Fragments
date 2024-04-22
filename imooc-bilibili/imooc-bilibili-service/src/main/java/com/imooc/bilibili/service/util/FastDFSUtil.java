package com.imooc.bilibili.service.util;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.imooc.bilibili.domain.exception.ConditionException;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class FastDFSUtil {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private AppendFileStorageClient appendFileStorageClient;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String PATH_KEY = "path-key:";

    private static final String UPLOADED_SIZE_KEY = "uploaded-size-key:";

    private static final String UPLOADED_NO_KEY = "uploaded-no-key:";

    private static final String DEFAULT_GROUP = "group1";

    private static final int SLICE_SIZE = 1024 * 1024 * 2;

    @Value("${fdfs.http.storage-addr}")
    private String httpFdfsStorageAddr;

    public String getFileType(MultipartFile file) {
        if (file == null) {
            throw new ConditionException("非法文件！");
        }
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index + 1);
    }

    // 上传
    public String uploadCommonFile(MultipartFile file) throws Exception {
        Set<MetaData> metaDataSet = new HashSet<>();
        String fileType = this.getFileType(file);
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), fileType, metaDataSet);
        return storePath.getPath();
    }

    public String uploadCommonFile(File file, String fileType) throws Exception {
        Set<MetaData> metaDataSet = new HashSet<>();
        StorePath storePath = fastFileStorageClient.uploadFile(new FileInputStream(file),
                file.length(), fileType, metaDataSet);
        return storePath.getPath();
    }

    // 上传可以断点续传的文件
    public String uploadAppenderFile(MultipartFile file) throws Exception {
        // 获取文件类型
        String fileType = this.getFileType(file);
        // 上传文件到存储服务器
        StorePath storePath = appendFileStorageClient.uploadAppenderFile(
                DEFAULT_GROUP,// 存在哪个阻力
                file.getInputStream(), // 转成inputStream流
                file.getSize(), // 获取文件大小
                fileType);// 获取文件类型

        // 返回文件路径
        return storePath.getPath();
    }

    public void modifyAppenderFile(MultipartFile file, String filePath, long offset) throws Exception {
        // 修改文件内容
        appendFileStorageClient.modifyFile(
                DEFAULT_GROUP,// 存在哪个阻力
                filePath,
                file.getInputStream(),
                file.getSize(),
                offset // 偏移量
        );
    }

    public String uploadFileBySlices(
            MultipartFile file,
            String fileMd5,
            Integer sliceNo,
            Integer totalSliceNo
    ) throws Exception {
        // 判断参数是否异常，如果异常则抛出异常
        if (file == null || sliceNo == null || totalSliceNo == null) {
            throw new ConditionException("参数异常！");
        }
        // 定义Redis中存储的key值
        String pathKey = PATH_KEY + fileMd5;
        String uploadedSizeKey = UPLOADED_SIZE_KEY + fileMd5;
        String uploadedNoKey = UPLOADED_NO_KEY + fileMd5;

        // 从Redis中获取已上传的文件大小
        String uploadedSizeStr = redisTemplate.opsForValue().get(uploadedSizeKey);
        Long uploadedSize = 0L;
        if (!StringUtil.isNullOrEmpty(uploadedSizeStr)) {
            uploadedSize = Long.valueOf(uploadedSizeStr);
        }

        if (sliceNo == 1) { // 判断是否为第一个分片
            // 上传文件的第一个分片到存储服务器
            String path = this.uploadAppenderFile(file);
            if (StringUtil.isNullOrEmpty(path)) {
                throw new ConditionException("上传失败！");
            }
            // 将文件路径和分片编号存入Redis
            redisTemplate.opsForValue().set(pathKey, path);
            redisTemplate.opsForValue().set(uploadedNoKey, "1");
        } else {
            // 获取文件路径
            String filePath = redisTemplate.opsForValue().get(pathKey);
            if (StringUtil.isNullOrEmpty(filePath)) {
                throw new ConditionException("上传失败！");
            }
            // 修改已上传的文件内容
            this.modifyAppenderFile(file, filePath, uploadedSize);
            // 分片编号加1
            redisTemplate.opsForValue().increment(uploadedNoKey);
        }

        // 修改已上传的文件大小
        uploadedSize += file.getSize();
        redisTemplate.opsForValue().set(uploadedSizeKey, String.valueOf(uploadedSize));

        // 如果所有分片全部上传完毕，则清空Redis中相关的key和value
        String uploadedNoStr = redisTemplate.opsForValue().get(uploadedNoKey);
        Integer uploadedNo = Integer.valueOf(uploadedNoStr);
        String resultPath = "";
        if (uploadedNo.equals(totalSliceNo)) {
            // 获取最终的文件路径
            resultPath = redisTemplate.opsForValue().get(pathKey);
            // 删除Redis中相关的key和value
            List<String> keyList = Arrays.asList(uploadedNoKey, pathKey, uploadedSizeKey);
            redisTemplate.delete(keyList);
        }
        // 返回最终结果路径
        return resultPath;
    }


    // 将文件分片
    public void convertFileToSlices(MultipartFile multipartFile) throws Exception {
        String fileType = this.getFileType(multipartFile);
        // 生成临时文件，将MultipartFile转为File
        File file = this.multipartFileToFile(multipartFile);
        long fileLength = file.length();
        int count = 1;
        for (int i = 0; i < fileLength; i += SLICE_SIZE) {
            // 以每个 SLICE_SIZE 为单位遍历文件

            // 创建一个可以随机访问的文件对象，以便进行读取操作
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

            // 设置文件指针位置到当前偏移量（i）
            randomAccessFile.seek(i);

            // 创建一个字节数组用于存储数据切片
            byte[] bytes = new byte[SLICE_SIZE];

            // 从文件中读取数据到字节数组中，并获取实际读取的字节数（len）
            int len = randomAccessFile.read(bytes);

            // 定义输出文件切片的路径，使用 'count' 和 'fileType' 构成命名模式
            String path = "/Users/hat/tmpfile/" + count + "." + fileType;

            // 创建一个表示输出文件切片的 File 对象
            File slice = new File(path);

            // 创建一个 FileOutputStream 用于向输出文件切片写入数据
            FileOutputStream fos = new FileOutputStream(slice);

            // 将字节数组中的数据写入到输出文件切片中
            fos.write(bytes, 0, len);

            // 关闭 FileOutputStream
            fos.close();

            // 关闭 RandomAccessFile
            randomAccessFile.close();

            // 增加 'count' 以在下一个切片中使用不同的文件名
            count++;
        }
        // 删除临时文件
        file.delete();
    }

    public File multipartFileToFile(MultipartFile multipartFile) throws Exception {
        // 获取上传文件的原始文件名
        String originalFileName = multipartFile.getOriginalFilename();

        // 通过原始文件名拆分文件名和扩展名
        String[] fileName = originalFileName.split("\\.");

        // 创建一个临时文件，使用拆分后的文件名和扩展名
        File file = File.createTempFile(fileName[0], "." + fileName[1]);

        // 将上传的 MultipartFile 转移（复制）到临时文件中
        multipartFile.transferTo(file);

        // 返回表示临时文件的 File 对象
        return file;
    }

    // 删除
    public void deleteFile(String filePath) {
        fastFileStorageClient.deleteFile(filePath);
    }


    public void viewVideoOnlineBySlices(HttpServletRequest request,
                                        HttpServletResponse response,
                                        String path) throws Exception {
        FileInfo fileInfo = fastFileStorageClient.queryFileInfo(DEFAULT_GROUP, path);
        long totalFileSize = fileInfo.getFileSize();
        String url = httpFdfsStorageAddr + path;
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, Object> headers = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            headers.put(header, request.getHeader(header));
        }
        String rangeStr = request.getHeader("Range");
        String[] range;
        if (StringUtil.isNullOrEmpty(rangeStr)) {
            rangeStr = "bytes=0-" + (totalFileSize - 1);
        }
        range = rangeStr.split("bytes=|-");
        long begin = 0;
        if (range.length >= 2) {
            begin = Long.parseLong(range[1]);
        }
        long end = totalFileSize - 1;
        if (range.length >= 3) {
            end = Long.parseLong(range[2]);
        }
        long len = (end - begin) + 1;
        String contentRange = "bytes " + begin + "-" + end + "/" + totalFileSize;
        response.setHeader("Content-Range", contentRange);
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Content-Type", "video/mp4");
        response.setContentLength((int) len);
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
        HttpUtil.get(url, headers, response);
    }

    public void downLoadFile(String url, String localPath) {
        fastFileStorageClient.downloadFile(DEFAULT_GROUP, url,
                new DownloadCallback<String>() {
                    @Override
                    public String recv(InputStream ins) throws IOException {
                        File file = new File(localPath);
                        OutputStream os = new FileOutputStream(file);
                        int len = 0;
                        byte[] buffer = new byte[1024];
                        while ((len = ins.read(buffer)) != -1) {
                            os.write(buffer, 0, len);
                        }
                        os.close();
                        ins.close();
                        return "success";
                    }
                });
    }
}
