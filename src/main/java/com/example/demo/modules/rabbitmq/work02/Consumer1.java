package com.example.demo.modules.rabbitmq.work02;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.example.demo.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;
/**
 * 
 * @author lit
 *
 * @date:  2019年8月27日 上午11:20:17  
 *
 */
public class Consumer1 {
    public static String QUEUE_NAME = "kill_queues";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, true, null);
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel) {
          
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                // TODO Auto-generated method stub
                String msg = new String(body);
                
                System.out.println("consumer1==>"+msg);
                try {
                    //获取线程名称
                    Thread.currentThread().getName();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }finally {
                    System.out.println("1==done");
                    channel.basicAck(envelope.getDeliveryTag(), false);//
                }
            }
        };
        boolean autoAck = false;//手动确认
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
