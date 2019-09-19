package com.example.demo.modules.rabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.example.demo.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/**
 * 轮训获取  
 * @author lit
 *
 * @date:  2019年8月27日 上午11:20:42  
 *
 */

public class Send {

    public static String QUEUE_NAME = "kill_queues";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, true, null);
        
        for(int i=0;i<50;i++) {
            String msg = "msg"+i;
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        }
        channel.close();
        connection.close();
    }
}
