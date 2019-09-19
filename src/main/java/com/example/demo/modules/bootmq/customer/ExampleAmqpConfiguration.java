/*
package com.example.demo.modules.bootmq.customer;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.config.RabbitMqConfig;
import com.example.demo.modules.bootmq.SerializeUtil;
import com.example.demo.modules.bootmq.TestUser;
import com.rabbitmq.client.Channel;

*/
/**
 * 消费者配置
 *
 * @author chenhf
 * @create 2017-10-30 下午3:14
 **//*

@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class ExampleAmqpConfiguration {
    @Bean("testQueueContainer")
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //消费者监听的队列
        container.setQueueNames("TESTQUEUE");
        container.setMessageListener(exampleListener());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }


    @Bean("testQueueListener")
    public ChannelAwareMessageListener exampleListener() {
        return new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                TestUser testUser = (TestUser) SerializeUtil.unserialize(message.getBody());
                //通过设置TestUser的name来测试回调，分别发两条消息，一条UserName为1，一条为2，查看控制台中队列中消息是否被消费
                if ("2".equals(testUser.getUserName())){
                    System.out.println("2接收成功"+testUser.toString());
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                }
                if ("3".equals(testUser.getUserName())){
                    System.out.println("3接收成功"+testUser.toString());
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                }
                if ("4".equals(testUser.getUserName())){
                    System.out.println("4接收成功"+testUser.toString());
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                }
                if ("1".equals(testUser.getUserName())){
                    System.out.println(testUser.toString());
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
                }

            }
        };
    }
}*/
