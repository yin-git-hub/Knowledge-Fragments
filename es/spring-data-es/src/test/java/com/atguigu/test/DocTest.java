package com.atguigu.test;

import com.atguigu.dao.GoodsDao;
import com.atguigu.pojo.Goods;
import com.atguigu.pojo.VideoInfoES;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocTest {

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 添加文档
     * */
    @Test
    public void saveTest(){
        Goods goods = new Goods();
        goods.setId("2");
        goods.setGoodsName("小米手机");
        goods.setStore(100);
        goods.setPrice(5000);
        // goodsDao.save(goods);
        System.out.println("添加成功...");
    }

    /**
     * 根据ID查询文档
     * */
    @Test
    public void findById(){

        // Goods goods1 = goodsDao.findByGoodsNameLike("11手88");
        // List<Goods> goods1 = goodsDao.findByGoodsName("手机");

        List<VideoInfoES> goods1 = goodsDao.findByUserId(1L + "");
        System.out.println(goods1);
    }

}
