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
import com.example.demo.config.RabbitMqEnum;
import com.example.demo.modules.bootmq.SerializeUtil;
import com.example.demo.modules.bootmq.TestUser;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;

@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class FanoutAmqpConfiguration {

    @Bean("fanoutTest1Container")
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("FANOUT");
        container.setMessageListener(exampleListener1());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }


    @Bean("fanoutTest1Listener")
    public ChannelAwareMessageListener exampleListener1(){
        return new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                TestUser testUser = (TestUser) SerializeUtil.unserialize(message.getBody());
                System.out.println("FANOUT--消費者->："+testUser.toString());
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
               //重新發送消息
                // channel.basicPublish(RabbitMqEnum.Exchange.CONTRACT_FANOUT.getCode(), RabbitMqEnum.QueueName.FANOUT.getCode(), new BasicProperties(), message.getBody());
            }
        };
    }



}
