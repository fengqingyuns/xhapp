package com.example.demo.modules.rabbitmqboot.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleReceiver.class);
    //監聽simple隊列，如果沒有此隊列先進行創建簡單隊列消費者 返回值必須void
    @RabbitListener(queuesToDeclare = @Queue("simple"))
    @RabbitHandler
    public void SimpleReceiver(String content) {
        LOGGER.info("---> :{}", content);
    }
}
