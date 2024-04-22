package org.example.returner.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 回退模式： 当消息发送给Exchange后,Exchange路由到Queue失败时 才会执行 ReturnCallBack
     * 步骤：
     * 1. 开启回退模式：publisher-returns="true"
     * 2. 设置ReturnCallBack
     * 3. 设置Exchange处理消息的模式：
     *      1). 如果消息没有路由到Queue,则丢弃消息（默认）
     *      2). 如果消息没有路由到Queue,返回给消息发送方ReturnCallBack
     *            rabbitTemplate.setMandatory(true);
     */
    @Test
    public void testReturn() {

        // 设置交换机处理失败消息的模式
        rabbitTemplate.setMandatory(true);

        // 2.设置ReturnCallBack
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * @param message   消息对象
             * @param replyCode 错误码
             * @param replyText 错误信息
             * @param exchange  交换机
             * @param routingKey 路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("return 执行了....");

                System.out.println(message);
                System.out.println(replyCode);
                System.out.println(replyText);
                System.out.println(exchange);
                System.out.println(routingKey);

                // 处理
            }
        });

        // 3. 发送消息
        rabbitTemplate.convertAndSend("spring_topic_exchange", "confirm", "message confirm....");
        // rabbitTemplate.convertAndSend("spring_topic_exchange", "guigu", "message confirm....");
    }
}
