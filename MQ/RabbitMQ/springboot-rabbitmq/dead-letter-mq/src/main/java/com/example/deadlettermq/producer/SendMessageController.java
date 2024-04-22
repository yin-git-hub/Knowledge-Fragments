package com.example.deadlettermq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.example.deadlettermq.config.RabbitMQConfig.BUSINESS_EXCHANGE_NAME;

@RestController
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;  // 使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {

        String messageData = "deadletter test message, hello!";

        // 将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend(
                BUSINESS_EXCHANGE_NAME,
                "",
                messageData);
        return "ok";
    }


}
