package com.atguigu.pojo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Author: yin7331
 * Date: 2023/11/16 5:50
 * Describe:
 */
@Data
@Document(indexName = "goods")
public class Payment {
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text)
    private String SERIAL;
}
