package cn.itcast;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Author: yin7331
 * Date: 2023/10/26 15:43
 * Describe:
 */
@Slf4j(topic = "c.MyTest")
public class MyTest {
    public static void main(String[] args) throws Exception{
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,
                3,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(9)
        );



        Future<Object> submit = pool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "ok";
            }
        });

        log.debug("{}",submit.get());



    }
}
