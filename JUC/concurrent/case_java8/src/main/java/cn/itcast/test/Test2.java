package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;
/**
 * 第2种方式创建线程
 */
@Slf4j(topic = "c.Test2")
public class Test2 {
    public static void main(String[] args) {
        Runnable r = () -> {
            log.debug("running");
        };

        Thread t = new Thread(r, "t2");

        t.start();
    }
}
