package com.atguigu.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 弹幕
 * @TableName scrolling
 */
@Data
@Document(indexName = "goods")
public class Scrolling implements Serializable {
    /**
     *
     */
    @Field(type = FieldType.Keyword)
    private Long id;

    /**
     *
     */
    @Field(type = FieldType.Keyword)
    private Long userId;

    /**
     *
     */
    @Field(type = FieldType.Keyword)
    private Long videoId;//reference
    /**
     * 弹幕内容
     */
    @Field(type = FieldType.Text)
    private String scrollingContext;
    /**
     * 弹幕出现的相对时间
     */
    @Field(type = FieldType.Long)
    private Long relativeTime;

    /**
     *
     */
    @Field(type = FieldType.Date)
    private Date createDate;

    /**
     *
     */
    @Field(type = FieldType.Date)
    private Date updateDate;
}
