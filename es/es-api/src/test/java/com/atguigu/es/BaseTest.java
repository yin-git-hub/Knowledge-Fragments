package com.atguigu.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Before;

public abstract class BaseTest {

    protected static final String INDEX = "student";

    protected RestHighLevelClient client;

    @Before
    public void before() {
        client = new RestHighLevelClient(RestClient
                .builder(new HttpHost("127.0.0.1", 9200, "http")));
    }

}
