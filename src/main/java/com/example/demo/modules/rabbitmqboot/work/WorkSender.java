package com.example.demo.modules.rabbitmqboot.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("WorkSender")
public class WorkSender {

    private static final Logger LOGGER  = LoggerFactory.getLogger(WorkSender.class);
    @Autowired
    private AmqpTemplate amqpTemplate;
    
    @GetMapping("workSender")
    public void workSender(String content) {
        LOGGER.info("content:{}", content);
        this.amqpTemplate.convertAndSend("work", content);
    }
}
