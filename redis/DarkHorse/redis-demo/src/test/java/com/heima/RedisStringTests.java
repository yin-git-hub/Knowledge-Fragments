package com.heima;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heima.redis.pojo.User;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class RedisStringTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString() {
        // 写入一条String数据
        stringRedisTemplate.opsForValue().set("verify:phone:13600527634", "124143");
        // 获取string数据
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testSaveUser() throws JsonProcessingException {
        // 创建对象
        User user = new User("虎哥", 21);
        // 手动序列化
        String json = mapper.writeValueAsString(user);
        // 写入数据
        stringRedisTemplate.opsForValue().set("user:200", json);

        // 获取数据
        String jsonUser = stringRedisTemplate.opsForValue().get("user:200");
        // 手动反序列化
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println("user1 = " + user1);
    }

    @Test
    void testHash() {
        stringRedisTemplate.opsForHash().put("user:400", "name", "虎哥");
        stringRedisTemplate.opsForHash().put("user:400", "age", "21");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println("entries = " + entries);
    }

    @Test
    void name() {
        stringRedisTemplate.boundHashOps("ScrollingKey").put("123456","qqqq");
        stringRedisTemplate.boundHashOps("ScrollingKey").put("123456","qqqq");
        String o = (String) stringRedisTemplate.boundHashOps("SrollingKey").get("123456");
        System.out.println("o = " + o);
    }
    @Data
    class Student{
        int age = 18;
        String name = "ui";
    }
    @Test
    void test1() {
        ListOperations<String, String> listOps = stringRedisTemplate.opsForList();

// 向Redis列表"1"的左侧添加元素"2"和"1"
        listOps.leftPush("1", "2");
        listOps.leftPush("1", "1");
        List<String> range = stringRedisTemplate.opsForList().range("1", 0, -1);


        System.out.println(range);
    }
}
