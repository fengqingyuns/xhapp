package com.example.demo.modules.rabbitmq.routing;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.example.demo.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/**
 * 路由模式
 * @author lit
 *
 * @date:  2019年8月27日 下午6:25:00  
 *
 */
public class Send {

    public static String EXCHANGE_NAME = "exchange_tests";
    public static void main(String[] args) throws IOException, TimeoutException {
        
        
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");//路由模式
        channel.basicQos(1);
        String msg = "hello";
        String routingKey = "info";
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
        System.out.println("msg==>"+msg);
        channel.close();
        connection.close();
    }
}
