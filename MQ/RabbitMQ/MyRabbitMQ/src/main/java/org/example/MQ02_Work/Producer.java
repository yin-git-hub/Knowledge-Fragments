package org.example.MQ02_Work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.utils.ConnectionUtil;

public class Producer {
    static final String QUEUE_NAME = "work_queue";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        for (int i = 1; i <= 10; i++) {
            String body = "消息"+i;
            channel.basicPublish("",QUEUE_NAME,null,body.getBytes());
        }
        ConnectionUtil.closeResource(channel, connection);
    }
}
