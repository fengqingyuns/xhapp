package com.example.demo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.modules.bootmq.callback.MsgSendConfirmCallBack;

/**
 * RabbitMq配置文件读取类
 *
 * @author chenhf
 * @create 2017-10-23 上午9:31
 **/

@Configuration
//@ConfigurationProperties(prefix = "spring.rabbitmq")

public class RabbitMqConfig {

    @Value("${spring.rabbitmq.addresses}")
    private String addresses;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.publisher-confirms}")
    private Boolean publisherConfirms;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;


    // 构建mq实例工厂
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPublisherConfirms(publisherConfirms);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setConfirmCallback(msgSendConfirmCallBack());
        return template;
    }
    
    
/*@Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//需要消费者回调
    public RabbitTemplate rabbitTemplate(){
       RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory());
       //rabbitTemplate.setConfirmCallback(msgSendConfirmCallBack());
      // rabbitTemplate.setReturnCallback(msgSendReturnCallBack());
       rabbitTemplate.setMandatory(true);
       return rabbitTemplate;
    }*//*

    */
/**
     * 消息确认机制
     * Confirms给客户端一种轻量级的方式，能够跟踪哪些消息被broker处理，
     * 哪些可能因为broker宕掉或者网络失败的情况而重新发布。
     * 确认并且保证消息被送达，提供了两种方式：发布确认和事务。(两者不可同时使用)
     * 在channel为事务时，不可引入确认模式；同样channel为确认模式下，不可使用事务。
     * @return
     */

   
    @Bean
    public MsgSendConfirmCallBack msgSendConfirmCallBack(){
       return new MsgSendConfirmCallBack();
    }

  /*  @Bean
    public MsgSendReturnCallBack msgSendReturnCallBack(){
       return new MsgSendReturnCallBack();
    }*/

}

