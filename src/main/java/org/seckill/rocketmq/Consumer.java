package org.seckill.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
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
@Configuration
public class Consumer {

    private static final Logger logger =
            LoggerFactory.getLogger(Consumer.class);

    @Value("${rocketmq.producer.PushConsumer}")
    private String pushConsumer;

    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Bean
    public DefaultMQPullConsumer getConsumer(){
        DefaultMQPullConsumer consumer = new
            DefaultMQPullConsumer(pushConsumer);
        consumer.setNamesrvAddr(namesrvAddr);
        try {
            consumer.start();
        } catch (MQClientException e) {
            logger.debug(e.getErrorMessage());
        }
        return consumer;
    }
    public static void main(String[] args) throws Exception {



        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("please_rename_unique_group_name_5");

        consumer.setNamesrvAddr("localhost:9876");

        consumer.start();

        Set<MessageQueue> queueSet = consumer.fetchSubscribeMessageQueues("TopicTest");
        for(MessageQueue queue : queueSet) {
            while (true) {

                PullResult result = consumer.pull(queue, "*", 20, 100);

                System.out.println(result);
                System.out.println(queue.getQueueId());

            }
        }

        consumer.shutdown();
    }


}
