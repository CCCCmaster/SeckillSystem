package org.seckill.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.seckill.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Idea
 *
 * @author ccccmaster
 * @date: 18-7-27
 * @time: 下午5:04
 */
//@Configuration
public class Consumer {

//    private static final Logger logger =
//            LoggerFactory.getLogger(Consumer.class);
//
//    @Value("${rocketmq.consumer.PushConsumer}")
//    private String pushConsumer;
//
//    @Value("${rocketmq.namesrvAddr}")
//    private String namesrvAddr;
//
//    @Bean
//    public DefaultMQPullConsumer getConsumer(){
//        DefaultMQPullConsumer consumer = new
//            DefaultMQPullConsumer(pushConsumer);
//        consumer.setNamesrvAddr(namesrvAddr);
//        try {
//            consumer.start();
//        } catch (MQClientException e) {
//            logger.debug(e.getErrorMessage());
//        }
//        return consumer;
//    }
//}
//
//class MessageListenerConcurrentlyImpl implements MessageListenerConcurrently {
//    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
//                                                    ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//        for (MessageExt ext : list) {
//            Object p = IOUtils.readObj(ext.getBody());
//        }
//        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//    }

}

