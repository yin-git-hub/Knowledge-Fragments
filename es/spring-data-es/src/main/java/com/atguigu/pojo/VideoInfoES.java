package com.atguigu.pojo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "videoinfoes")
public class VideoInfoES {
    @Field(type = FieldType.Keyword)
    String id;
    @Field(type = FieldType.Keyword)
    Long userId;
    @Field(type = FieldType.Keyword)
    Long videoId;
    @Field(type = FieldType.Text)
    String videoName;
    @Field(type = FieldType.Keyword)
    String videoSummary;
    @Field(type = FieldType.Keyword)
    private Integer area;
    @Field(type = FieldType.Keyword)
     private Long videoSize;
    @Field(type = FieldType.Keyword)
    private String videoCover;
}
