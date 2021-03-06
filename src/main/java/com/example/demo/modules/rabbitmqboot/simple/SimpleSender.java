package com.example.demo.modules.rabbitmqboot.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("SimpleSender")
public class SimpleSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSender.class);
    @Autowired
    private AmqpTemplate amqpTemplate;
    
    @GetMapping("simple")
    public void simpleSend(String content) {
       
        LOGGER.info("接收的值 :{}", content);
        this.amqpTemplate.convertAndSend("simple", content);
    }
}
