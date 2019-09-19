package com.example.demo.modules.rabbitmq.work;

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
                }
            }
        };
        boolean autoAck = true;//（自动确认）一旦rqbbitmq将消息分发给消费者，就会从内存中删除
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
