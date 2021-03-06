/*package com.example.demo.modules.rabbitmqboot.fanout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fanoutSender")
public class FanoutSender {

    private static final Logger logger = LoggerFactory.getLogger(FanoutSender.class);
    @Autowired
    private AmqpTemplate amqpTemplate;
    
    @GetMapping("fanoutSender")
    public void fanout (String content) {
        
        logger.info("fanout sender 發送消息 :{}", content);
        this.amqpTemplate.convertAndSend("fanout.test", "", content);
    }
}
*/