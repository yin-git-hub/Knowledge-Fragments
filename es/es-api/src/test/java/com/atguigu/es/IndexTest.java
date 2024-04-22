package com.atguigu.es;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;

public class IndexTest extends BaseTest{

    //创建索引
    @Test
    public void createIndex(){
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX);
        try {
            createIndexRequest.mapping("{\n" +
                    "    \"properties\": {\n" +
                    "      \"name\": {\n" +
                    "        \"type\": \"keyword\",\n" +
                    "        \"index\": true,\n" +
                    "        \"store\": true\n" +
                    "      },\n" +
                    "      \"age\": {\n" +
                    "        \"type\": \"integer\",\n" +
                    "        \"index\": true,\n" +
                    "        \"store\": true\n" +
                    "      },\n" +
                    "      \"remark\": {\n" +
                    "        \"type\": \"text\",\n" +
                    "        \"index\": true,\n" +
                    "        \"store\": true,\n" +
                    "        \"analyzer\": \"ik_max_word\",\n" +
                    "        \"search_analyzer\": \"ik_smart\"\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }", XContentType.JSON);
            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            System.out.println(createIndexResponse.isAcknowledged());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查看索引
    @Test
    public void getIndex(){
        GetIndexRequest request = new GetIndexRequest(INDEX);
        try {
            GetIndexResponse getIndexResponse = client.indices().get(request, RequestOptions.DEFAULT);
            System.out.println(getIndexResponse.getMappings());
            System.out.println(getIndexResponse.getSettings());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除索引
    @Test
    public void deleteIndex(){
        DeleteIndexRequest request = new DeleteIndexRequest(INDEX);
        try {//fuzzy
            AcknowledgedResponse acknowledgedResponse
                    = client.indices().delete(request, RequestOptions.DEFAULT);
            System.out.println(acknowledgedResponse.isAcknowledged());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
