package org.seckill.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by Idea
 *
 * @author ccccmaster
 * @date: 18-7-27
 * @time: 下午5:05
 */
@Configuration
public class Producer {
    /* 日志 */
    private static final Logger logger = LoggerFactory.getLogger(
            Producer.class
    );

    @Value("${rocketmq.producer.producerGroup}")
    private String producerGroup;

    @Value("${rocketmq.namesrvAddr}")
    private String namearvAddr;

    /**
     * 消息生产者
     * @return DefaultMQProducer 默认的生产者
     */
    @Bean
    public DefaultMQProducer getProducer(){
        DefaultMQProducer mqProducer = new
                DefaultMQProducer(producerGroup);
        mqProducer.setNamesrvAddr(namearvAddr);
        try {
            mqProducer.start();
        } catch (MQClientException e) {
            logger.debug(e.getErrorMessage());
        }
        return mqProducer;
    }

    /**
     * 把对象转换成能直接在队列中传输的Message
     * @param topic Topic
     * @param tags Tag
     * @param info 待发送的对象
     * @return Message 消息对象
     */
    public static Message messageWrapper(String topic, String tags,
                                         Object info){
        byte[] byteArr = toByteArray(info);
        Message msg = new Message(
                topic, tags, byteArr
        );
        return msg;
    }

    /**
     * 把对象转换为字节数组
     * @param o 对象
     * @return byte[] 字节数组
     */
    public static byte[] toByteArray(Object o){
        ByteArrayOutputStream byteArrayOutputStream = new
                ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new
                    ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(o);
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }


}
