package com.example.demo.modules.bootmq.callback;

import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
/*
**
* 消息发送到交换机确认机制
* @author zhuzhe
* @date 2018/5/25 15:53
* @email 1529949535@qq.com
*/
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {

   @Override
   public void confirm(CorrelationData correlationData, boolean ack, String cause) {
       System.out.println("MsgSendConfirmCallBack  , 回调id:" + correlationData);
       if (ack) {
           System.out.println("消息發送到交換機成功");
       } else {
           System.out.println("消息發送到交換機失敗失败:" + cause+"\n重新发送");
       }
   }
}
