package com.example;


import org.junit.Test;

import java.io.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    File file = new File("E:\\resources\\baidu\\java\\Springboot3+微服务实战12306高性能售票系统\\第5章 使用Vue3 + Vue CLI 实现系统前端模块的搭建\\a.mp4");

    @Test
    public void fileInputStreamTest() throws Exception {
        FileInputStream fis = new FileInputStream(file);

        FileOutputStream fos = new FileOutputStream("a2.mp4");
        byte temp[] = new byte[1024];
        int len;
        while ((len = fis.read(temp)) > 0) {
            fos.write(temp, 0, len);
        }
        fis.close();
        fos.close();
    }

    @Test
    public void c() throws Exception {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("a1.mp4"));
        byte[] temp = new byte[1024];
        int len;
        while ((len = bis.read(temp)) > 0) {
            bos.write(temp, 0, len);
        }
        Thread.sleep(1000 * 3);

        bis.close();
        bos.close();

        File fileLocation = new File("a1.mp4");
        fileLocation.delete();

    }

    @Test
    public void inputStreamReader() throws Exception {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("aa.mp4"));
        char[] temp = new char[1024];
        int len;
        while ((len = isr.read(temp)) > 0) {
            osw.write(temp, 0, len);
        }

    }

}
