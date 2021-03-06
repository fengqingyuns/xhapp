package com.example.demo.modules.bootmq;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.config.RabbitMqEnum;

/**
 * rabbitmq发送消息工具类
 *
 * @author li.t
 * @create 2017-10-26 上午11:10
 **/

@Component
public class RabbitMqSender implements RabbitTemplate.ConfirmCallback{
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqSender.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        //this.rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        System.out.println("消息id: "+correlationData);
        if (ack){
           System.out.println("消息发送到交换机-成功。");
        }else {
           System.out.println("消息发送到交换机-失败，重新发送！");
        }
    }

    
    /**
     * 发送到 指定routekey的指定queue
     * @param routeKey
     * @param obj
     */
    public void sendRabbitmqFanout(Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send:fanout " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.CONTRACT_FANOUT.getCode(),"",  obj, correlationData);
    }
    /**
     * 发送到 指定routekey的指定queue
     * @param routeKey
     * @param obj
     */
    public void sendRabbitmqDirect(String routeKey,Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send:direct " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.CONTRACT_DIRECT.getCode(), routeKey , obj, correlationData);
    }

    /**
     * 所有发送到Topic Exchange的消息被转发到所有关心RouteKey中指定Topic的Queue上
     * @param routeKey
     * @param obj
     */
    public void sendRabbitmqTopic(String routeKey,Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send:topic " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.CONTRACT_TOPIC.getCode(), routeKey , obj, correlationData);
    }
}
