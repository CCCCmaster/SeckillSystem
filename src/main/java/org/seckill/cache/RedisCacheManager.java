package org.seckill.cache;

import org.springframework.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.Callable;

public class RedisCacheManager implements Cache {
    private RedisTemplate redisTemplate;
    private String name;

    /**
     * 得到该缓存的名字
     * @return Cache`s Name
     */
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return redisTemplate;

    }

    @Override
    public ValueWrapper get(Object key) {
        return null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }
}
