package com.example.deadlettermq.consumer;

import com.example.deadlettermq.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.deadlettermq.config.RabbitMQConfig.BUSINESS_QUEUEA_NAME;
import static com.example.deadlettermq.config.RabbitMQConfig.BUSINESS_QUEUEB_NAME;

@Slf4j
@Component
public class BusinessMessageReceiver {
    @RabbitListener(queues = BUSINESS_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        try{
            String msg = new String(message.getBody());
            System.out.println("收到业务消息A："+ msg);
            int i = 1 / 0;
        }catch (Exception e){
              /**
               * 第三个参数：requeue：重回队列。
               * 设置为true,则消息重新回到queue,broker会重新发送该消息给消费端
             */
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }



    @RabbitListener(queues = BUSINESS_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        System.out.println("收到业务消息B：" + new String(message.getBody()));
        /**
         * 第一个参数：表示收到的标签
         * 第二个参数：如果为true表示可以签收所有的消息
         */
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
