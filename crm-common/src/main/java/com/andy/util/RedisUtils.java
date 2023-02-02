package com.andy.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    private static RedisTemplate<String,String> template;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @PostConstruct
    public void init(){
        RedisUtils.template = this.redisTemplate;
    }

    public static void set(String key,String value){
        template.opsForValue().set(key,value);
    }

    public static void set(String key, String value, long time, TimeUnit unit){
        template.opsForValue().set(key,value,time,unit);
    }

    public static void hset(String key, Map value){
        template.opsForHash().putAll(key,value);
    }

    public static Map<Object, Object> hget(String key){
        return template.opsForHash().entries(key);
    }

    /**
     * 设置过期时间
     * @param key
     * @param timeout
     */
    public static void expire(String key,long timeout){
        template.expire(key,timeout,TimeUnit.SECONDS);
    }

    public static String get(String key) {
        return template.opsForValue().get(key);
    }
}
