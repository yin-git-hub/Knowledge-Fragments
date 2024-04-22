package com.atguigu.es;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.pojo.Student;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;

public class DocumentTest extends BaseTest{

    //创建文档
    @Test
    public void createDocument(){
        IndexRequest request = new IndexRequest(INDEX);
        request.id("1");
        Student student = new Student();
        student.setAge(18);
        student.setName("robin");
        student.setRemark("good man");
        request.source(JSONObject.toJSONString(student), XContentType.JSON);
        try {
            IndexResponse index = client.index(request, RequestOptions.DEFAULT);
            System.out.println(index.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //修改文档
    @Test
    public void updateDocuemnt(){
        UpdateRequest request = new UpdateRequest(INDEX,"1");
        try {
            Student student = new Student();
            student.setRemark("very good man");
            request.doc(JSONObject.toJSONString(student), XContentType.JSON);
            UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
            System.out.println(response.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据ID查询
    @Test
    public void getDocument(){
        GetRequest request = new GetRequest(INDEX,"1");
        try {
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            System.out.println(response.getSourceAsString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //批量操作
    @Test
    public void bulkDocument(){
        BulkRequest request = new BulkRequest();
        Student student = new Student();
        for(int i=0;i<10;i++){
            student.setAge(18 + i);
            student.setName("robin" + i);
            student.setRemark("good man " + i);
            request.add(new IndexRequest(INDEX).id(String.valueOf(10 + i)).source(JSONObject.toJSONString(student), XContentType.JSON));
        }
        try {

            BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
            for(BulkItemResponse itemResponse : response.getItems()){

                System.out.println(itemResponse.isFailed());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除文档
    @Test
    public void deleteDocument(){
        DeleteRequest request = new DeleteRequest(INDEX,"11");
        try {
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            System.out.println(response.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
