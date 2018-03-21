package org.seckill.cache;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * 使用Google的protostuff序列化工具替代默认的序列化工具
 * 未完成 待续
 */
public class ProtobuffRedisSerializer{//<T> implements RedisSerializer<T> {

//    private Class<T> type; // 对象的类类型
//    public ProtobuffRedisSerializer(Class<T> type){
//        this.type = type;
//    }
//
//
//    @Override
//    public byte[] serialize(Object o) throws SerializationException {
//        return new byte[0];
//    }
//
//    @Override
//    public T deserialize(byte[] bytes) throws SerializationException {
//        return null;
//    }
}
