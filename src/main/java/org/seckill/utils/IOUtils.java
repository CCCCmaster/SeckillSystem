package org.seckill.utils;

import org.seckill.rocketmq.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

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
            IOUtils.class
    );
    /**
     * 把对象转换为字节数组
     * @param o 对象
     * @return byte[] 字节数组
     */
    public static byte[] toByteArray(Object o){
        ByteArrayOutputStream byteArrayOutputStream = new
                ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new
                    ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(o);
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        byte[] arr = byteArrayOutputStream.toByteArray();
        try {
            if(objectOutputStream != null){
                objectOutputStream.close();
            }
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static Object readObj(byte[] body){
        Object obj = null;
        ByteArrayInputStream os = new ByteArrayInputStream(body);
        ObjectInputStream oos = null;
        try {
            oos = new ObjectInputStream(os);
            obj = oos.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if(oos != null){
                oos.close();
            }
            os.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return obj;
    }

}
