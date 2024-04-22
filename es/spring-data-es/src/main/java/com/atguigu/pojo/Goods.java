package com.atguigu.pojo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Document(indexName = "goods")
public class Goods implements Serializable {

    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text)
    private String goodsName;
    @Field(type = FieldType.Integer)
    private Integer store;
    @Field(type = FieldType.Double)
    private double price;

}
