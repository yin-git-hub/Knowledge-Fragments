package org.example.DeadQueue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Author: yin7331
 * Date: 2023/10/15 16:56
 * Describe:
 */


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class DeadTest {
    @Resource
    RabbitTemplate rabbitTemplate;
    @Test
    public void testDlx(){
        //1. 测试过期时间，死信消息
        rabbitTemplate.convertAndSend(
                "test_exchange_dlx",
                "test.dlx.haha",
                "我是一条消息,我会死吗？");
    }
}
