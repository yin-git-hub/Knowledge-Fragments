package com.atguigu.dao;

import com.atguigu.pojo.Goods;
import com.atguigu.pojo.VideoInfoES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDao extends ElasticsearchRepository<VideoInfoES, String> {
    // Goods findByGoodsNameLike(String goodsName);
    //
    // List<Goods> findByGoodsName(String goodsName);
    // List<Goods> findById(String id);
    List<VideoInfoES> findByUserId(String goodsName);
}
