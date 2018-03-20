package org.seckill.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
    private JedisPool jedisPool;
    public RedisDao(String ip, int port){
        jedisPool = new JedisPool(ip, port);
    }
    private RuntimeSchema<Seckill> seckillRuntimeSchema = RuntimeSchema.createFrom(Seckill.class);
    public Seckill getSeckill(long seckillId){
        try{
            Jedis jedis = jedisPool.getResource();
            try{
                //采用自定义序列化方式 优化速度
                String key = "seckill:"+seckillId;
                byte[] bytes = jedis.get(key.getBytes());
                if(bytes != null){
                    //先设置空对象
                    Seckill  seckill = seckillRuntimeSchema.newMessage();
                    //使用Protostuff工具类转化
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, seckillRuntimeSchema);
                    return seckill;
                }
            }finally {
                jedis.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        return null;
    }
    public String putSeckill(Seckill seckill){
        try{
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "seckill:"+seckill.getSeckill_id();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, seckillRuntimeSchema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60 *60;
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                jedis.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
