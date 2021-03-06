package com.example.demo.modules.miaosha.mq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(exchange = @Exchange(value = "kill_exchang", durable = "true"), key = "kill_routing_key", value = @Queue(value = "sync-jdsales-msg-syn-jd", durable = "true")))
public class KillMqReceiver {

}
