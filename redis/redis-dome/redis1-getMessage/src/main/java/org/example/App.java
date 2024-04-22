package org.example;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.197.128", 6379);
        String pong = jedis.ping();
        System.out.println("连接成功：" + pong);
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");
        //keys *
        //查看当前库所有key    (匹配：keys *1)

        Set<String> keys = jedis.keys("*");
        System.out.println(keys.size());
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(jedis.exists("k1"));

        System.out.println(jedis.ttl("k1"));
        System.out.println(jedis.get("k1"));

        jedis.close();
    }

}
