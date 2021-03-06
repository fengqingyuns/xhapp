/*package com.example.demo.modules.rabbitmqboot.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver {

    private static final Logger logger = LoggerFactory.getLogger(FanoutReceiver.class);
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fanout1"),
               exchange = @Exchange("fanout.test")
            ))
    public void fanoutReceiver2(String content) {
        logger.info("fanout receiver 2:{}", content);
    }
    
    @RabbitHandler
    @RabbitListener(
        bindings = @QueueBinding(value = @Queue("fanout2"),
        exchange = @Exchange("fanout.test")
                ))
    public void fanoutReceiver1(String content) {
        logger.info("fanout receiver1:{}", content);
    }
    
    
}
*/