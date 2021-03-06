
package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * 用于配置交换机和队列对应关系
 * 新增消息队列应该按照如下步骤
 * 1、增加queue bean，参见queueXXXX方法
 * 2、增加queue和exchange的binding
 * @author chenhf
 * @create 2017-10-23 上午10:33
 **/

@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class RabbitMqExchangeConfig {
    
/** logger */

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqExchangeConfig.class);

    
/**
     * @Author:chenhf
     * @Description: 主题型交换机
     * @Date:下午5:49 2017/10/23
     * @param
     * @return
     */

    @Bean
    TopicExchange contractTopicExchangeDurable(RabbitAdmin rabbitAdmin){
        TopicExchange contractTopicExchange = new TopicExchange(RabbitMqEnum.Exchange.CONTRACT_TOPIC.getCode());
        rabbitAdmin.declareExchange(contractTopicExchange);
        logger.debug("完成主题型交换机bean实例化");
        return contractTopicExchange;
    }
    
/**
     * 直连型交换机
     */

    @Bean
    DirectExchange contractDirectExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange contractDirectExchange = new DirectExchange(RabbitMqEnum.Exchange.CONTRACT_DIRECT.getCode());
        rabbitAdmin.declareExchange(contractDirectExchange);
        logger.debug("完成直连型交换机bean实例化");
        return contractDirectExchange;
    }
    
    /**
     * fanout交換機
     * @author lit
     * @desc:
     * @date:  2020年9月13日 下午7:28:51  
     *
     * @param rabbitAdmin
     * @return
     */
    @Bean
    FanoutExchange contractFanoutExchange(RabbitAdmin rabbitAdmin) {
        FanoutExchange contractFanoutExchange = new FanoutExchange(RabbitMqEnum.Exchange.CONTRACT_FANOUT.getCode());
        rabbitAdmin.declareExchange(contractFanoutExchange);
        logger.debug("完成fanout交换机bean实例化");
        return contractFanoutExchange;
    }
    

    //在此可以定义队列
    
    //秒杀队列
    @Bean
    Queue killQueue(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue(RabbitMqEnum.QueueEnum.KILL_ROUTING_KEY.getCode());
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue queueTest(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue(RabbitMqEnum.QueueName.TESTQUEUE.getCode());
        rabbitAdmin.declareQueue(queue);
        logger.debug("测试队列实例化完成");
        return queue;
    }
    //fanout 1
    @Bean
    Queue queueFanoutTest1(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue(RabbitMqEnum.QueueName.FANOUT.getCode());
        rabbitAdmin.declareQueue(queue);
        logger.debug("fanout测试队列1实例化完成");
        return queue;
    }
    //topic 1
    @Bean
    Queue queueTopicTest1(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue(RabbitMqEnum.QueueName.TOPICTEST1.getCode());
        rabbitAdmin.declareQueue(queue);
        logger.debug("话题测试队列1实例化完成");
        return queue;
    }
    //topic 2
    @Bean
    Queue queueTopicTest2(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue(RabbitMqEnum.QueueName.TOPICTEST2.getCode());
        rabbitAdmin.declareQueue(queue);
        logger.debug("话题测试队列2实例化完成");
        return queue;
    }
  //在此处完成队列和交换机绑定
    //秒杀队列和直连型交换机绑定
    @Bean
    Binding bindingKillQueue(Queue killQueue,DirectExchange exchange,RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(killQueue).to(exchange).with(RabbitMqEnum.QueueEnum.KILL_ROUTING_KEY.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("秒杀队列与direct交换机绑定完成");
        return binding;
    }
    
    
    @Bean
    Binding bindingQueueTest11(Queue queueFanoutTest1,FanoutExchange exchange,RabbitAdmin rabbitAdmin){
        Binding binding = BindingBuilder.bind(queueFanoutTest1).to(exchange);
        rabbitAdmin.declareBinding(binding);
        logger.debug("测试队列与fanout交换机绑定完成");
        return binding;
    }
    //在此处完成队列和交换机绑定
    @Bean
    Binding bindingQueueTest(Queue queueTest,DirectExchange exchange,RabbitAdmin rabbitAdmin){
        Binding binding = BindingBuilder.bind(queueTest).to(exchange).with(RabbitMqEnum.QueueEnum.TESTQUEUE.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("测试队列与直连型交换机绑定完成");
        return binding;
    }
    //topic binding1
    @Bean
    Binding bindingQueueTopicTest1(Queue queueTopicTest1,TopicExchange exchange,RabbitAdmin rabbitAdmin){
        Binding binding = BindingBuilder.bind(queueTopicTest1).to(exchange).with(RabbitMqEnum.QueueEnum.TESTTOPICQUEUE1.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("测试队列与话题交换机1绑定完成");
        return binding;
    }

    //topic binding2
    @Bean
    Binding bindingQueueTopicTest2(Queue queueTopicTest2,TopicExchange exchange,RabbitAdmin rabbitAdmin){
        Binding binding = BindingBuilder.bind(queueTopicTest2).to(exchange).with(RabbitMqEnum.QueueEnum.TESTTOPICQUEUE2.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("测试队列与话题交换机2绑定完成");
        return binding;
    }

}
