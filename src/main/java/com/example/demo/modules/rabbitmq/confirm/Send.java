package com.example.demo.modules.rabbitmq.confirm;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.example.demo.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    public static String EXCHANGE_NAME="exchange_names";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.confirmSelect();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String msg = "hello";
        String routKey = "add.add";
        channel.basicPublish(EXCHANGE_NAME,routKey , null, msg.getBytes());
        if(! channel.waitForConfirms()) {
            System.out.println("error");
        }else {
            System.err.println("success");
        }
        channel.close();
        connection.close();
    }
}
