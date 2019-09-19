package com.example.demo.modules.rabbitmq.dingyue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.example.demo.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/**
 * 订阅模式
 * @author lit
 *
 * @date:  2019年8月27日 下午6:36:32  
 *
 */
public class Send {
    public static String EXCHANGE_NAME = "exchange_test";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        String msg = "hello rabbitmq";
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//fanout 分发
        
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        System.out.println(msg);
        channel.close();
        connection.close();
    }
}
