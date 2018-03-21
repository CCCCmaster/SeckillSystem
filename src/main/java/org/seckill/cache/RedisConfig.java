package org.seckill.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * spring整合redis 缓存
 * Java配置redis
 * redis相关配置
 */
@Configuration // 配置类
@EnableCaching // 开启缓存
public class RedisConfig {
    /**
     * 配置redisTemplate
     * @return
     */
    @Bean(name="redisTemplate")
    public RedisTemplate initRedisTemplate() {
        JedisPoolConfig pc = new JedisPoolConfig();
        pc.setMaxIdle(5);
        pc.setMaxTotal(100);
        pc.setMaxWaitMillis(20000);
        //创建Jedis连接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(pc);
        connectionFactory.setHostName("localhost");
        connectionFactory.setPort(6379);
        //调用后初始化方法，没有它将抛出异常
        connectionFactory.afterPropertiesSet();
        //定义Redis序列化器
        RedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        RedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //定义RedisTemplate， 并设置连接工程
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;

    }


    /**
     * 配置缓存管理器
     * @param redisTemplate
     * @return
     */
    @Bean(name="cacheManager")
    public CacheManager initRedisCacheManager(@Autowired RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(600);
        List<String> cacheNames = new ArrayList<String>();
        cacheNames.add("cacheManager");
        cacheManager.setCacheNames(cacheNames);
        return cacheManager;
    }
}
