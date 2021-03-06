package com.example.demo.modules.bootmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class TestMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitMqSender rabbitMqSender;
    @RequestMapping("/msg")
    public String getString(String name) {
        TestUser testUser = new TestUser();
        testUser.setUserName(name);
        
       // rabbitMqSender.sendRabbitmqDirect("TESTQUEUE1",testUser);
       // rabbitMqSender.sendRabbitmqTopic("1.TEST.2",testUser);
      //  rabbitMqSender.sendRabbitmqTopic("lazy.12",testUser);
        rabbitMqSender.sendRabbitmqFanout(testUser);
       
        
        return "success";
    }
}
