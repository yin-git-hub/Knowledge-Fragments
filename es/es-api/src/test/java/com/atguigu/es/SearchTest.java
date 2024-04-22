package com.atguigu.es;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;

import java.io.IOException;

public class SearchTest extends BaseTest{

    private static final String MY_INDEX = "my_index";

    /**
     * dsl查询文档:
     * {
     *   "query": {
     *     "match": {
     *       "title": "华为智能手机"
     *     }
     *   }
     * }
     * */
    @Test
    public void search(){
        SearchRequest request = new SearchRequest(MY_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("title","华为智能手机"));
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            for(SearchHit hit : response.getHits().getHits()){
                System.out.println(hit.getSourceAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 高亮查询
     * */
    @Test
    public void highlightSearch(){
        SearchRequest request = new SearchRequest(MY_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("title","华为智能手机"));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.preTags("<b style='color:red'>");
        highlightBuilder.postTags("</b>");
        builder.highlighter(highlightBuilder);
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            for(SearchHit hit : response.getHits().getHits()){
                System.out.println(hit.getSourceAsMap().get("title") + ":" +hit.getHighlightFields().get("title").fragments()[0].string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 聚合查询
     * */
    @Test
    public void aggsSearch(){
        SearchRequest request = new SearchRequest(MY_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        AggregationBuilder aggregationBuilder = AggregationBuilders
                .terms("groupby_category").field("category");
        aggregationBuilder.subAggregation(AggregationBuilders.avg("avg_price").field("price"));
        builder.aggregation(aggregationBuilder);
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            Terms terms = aggregations.get("groupby_category");
            terms.getBuckets().forEach(bucket -> {
                Avg avg = bucket.getAggregations().get("avg_price");
                System.out.println(bucket.getKeyAsString() + ":" + bucket.getDocCount() + "," + avg.getValue());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
