package com.heima;

import com.heima.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private RedisTemplate  redisTemplate;

    @Test
    void testString() {
        // 写入一条String数据
        redisTemplate.opsForValue().set("name", "虎哥");
        redisTemplate.delete("kk1");
        // 获取string数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testSaveUser() {
        // 写入数据
        redisTemplate.opsForValue().set("user:100", new User("虎哥", 21));
        // 获取数据
        User o = (User) redisTemplate.opsForValue().get("user:100");
        System.out.println("o = " + o);
    }

    @Test
    public void test1(){

        String OBJECT_INFO_LIST = "com:minio:media:objectList";
        String key="minio-demo:460e2dbe-e4c2-4232-8a5b-60e054f35f0c.mp4";
        String minioObject="MinioObject(bucket=null, region=null, object=null, etag=null, size=0, deleteMarker=false, userMetadata=null)";

        redisTemplate.boundHashOps(OBJECT_INFO_LIST).put(key, minioObject);


    }
}
