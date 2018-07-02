package com.lambo.demo.mq.service;

import com.lambo.common.annotation.LogAround;
import com.lambo.demo.mq.controller.MqProviderTestController;
import com.lambo.mq.client.MQConsumerUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @ClassName MqConsumerTestService
 * @Descirption MQ消费者示例
 * @Author sunzhen
 * @Date 2018/6/30 11:30
 **/
public class MqConsumerTestService {

    Logger logger = LoggerFactory.getLogger(MqConsumerTestService.class);

    @LogAround("MQ消费者示例方法")
    public void ConsumeMessage(){
        logger.info("MQ消费者示例方法-------开始");
        DefaultMQPushConsumer consumer  = MQConsumerUtil.initComsumer();
        try{
            consumer.subscribe("LamboDemo", "TestTag");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
                    for (MessageExt message : messages) {
                        try{
                            String msg = new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET);
                            System.out.println(" Receive New Messages: " + msg);
                        }catch(Exception e){
                            logger.error("消息解码异常",e);
                        }
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        }catch (Exception e){
            logger.error("消费消息异常",e);
        }
        logger.info("MQ消费者示例方法-------结束");
    }
}
