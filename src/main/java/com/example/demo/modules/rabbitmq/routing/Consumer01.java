package com.example.demo.modules.rabbitmq.routing;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.example.demo.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Consumer01 {
    public static String EXCHANGE_NAME = "exchange_tests";
    public static String QUEUE_NAME="queue_test";
    public static void main(String[] args) throws IOException, TimeoutException {
        // TODO Auto-generated method stub
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, true, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "error");
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                // TODO Auto-generated method stub
                System.out.println("01"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
