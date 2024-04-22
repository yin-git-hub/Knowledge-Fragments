package com.imooc.bilibili.service.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class JsonHttpMessageConverterConfig {


    public static void main(String[] args){
        List<Object> list = new ArrayList<>();
        Object o = new Object();
        list.add(o);
        list.add(o);
        System.out.println(list.size());
        // 没有循环引用
        System.out.println(JSONObject.toJSONString(list));
        // 有循环引用
        System.out.println(JSONObject.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect));
    }

    @Bean
    @Primary
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,//有缩进和换行
                SerializerFeature.WriteNullStringAsEmpty,//为null的字段编程空字符串
                SerializerFeature.WriteNullListAsEmpty,//为null的list编程空字符串
                SerializerFeature.WriteMapNullValue,//为null的map编程空字符串
                SerializerFeature.MapSortField,//为数据排序，默认升序
                SerializerFeature.DisableCircularReferenceDetect//禁用循环引用 循环引用
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //如果使用feign进行微服务间的接口调用，则需要加上该配置
        fastConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpMessageConverters(fastConverter);
    }
}