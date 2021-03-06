package com.example.demo.modules.rabbitmqboot.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//放在類上，監聽到消息后悔交給@RabbitHandler的方法進行處理，如果有多個方法，會根據參數類型進行選擇
//@RabbitListener(queuesToDeclare = @Queue("work"))
public class WorkReceiver {

    private static final Logger LOGGER  = LoggerFactory.getLogger(WorkReceiver.class) ;
    @RabbitListener(queuesToDeclare = @Queue("work"))
    @RabbitHandler
    public void workReceiver1(String content) throws InterruptedException {
        LOGGER.info("work rec--1 開始消費", content);
        Thread.sleep(1000);
    }
    
    @RabbitListener(queuesToDeclare = @Queue("work"))
    @RabbitHandler
    public void workReceiver2(String content) throws InterruptedException {
        LOGGER.info("work rec--2 開始消費", content);
        Thread.sleep(1000);
    }
}
