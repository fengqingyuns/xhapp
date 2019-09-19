package com.example.demo.modules.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.example.demo.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    public static String QUEUE_NAME = "kill-queues";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //1.队列名称，2.是否为持久队列（该队列将在服务器重新启动后继续存在），3.如果声明独占队列（仅限于此连接），
        //4.如果声明自动删除队列，则自动删除为真（服务器将在不再使用时删除该队列），5.参数队列的其他属性（构造参数）Map<String, Object>
        channel.queueDeclare(QUEUE_NAME, true, false, true,null);
        String msg = "kill you";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        channel.close();
        connection.close();
    }
}
