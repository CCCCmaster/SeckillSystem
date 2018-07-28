package org.seckill.utils;

import org.seckill.rocketmq.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Idea
 *
 * @author ccccmaster
 * @date: 18-7-28
 * @time: 下午8:11
 */
public class IOUtils {
    /* 日志 */
    private static final Logger logger = LoggerFactory.getLogger(
            Producer.class
    );
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
