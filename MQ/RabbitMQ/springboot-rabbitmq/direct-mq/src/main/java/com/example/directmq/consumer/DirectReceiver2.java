package com.example.directmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DirectReceiver2 {

    @RabbitHandler
    @RabbitListener(queues = "testDirectQueue")// 监听的队列名称 TestDirectQueue
    public void process(Map testMessage) {
        System.out.println("DirectReceiver2消费者收到消息  : "
                + testMessage.toString());
    }

}
