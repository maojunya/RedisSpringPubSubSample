package com.app.nosql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by ozgur on 02.04.2014.
 */
public class RedisGetterSetterUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Object getValue(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setValue(final String key,final String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public void setValue(final String key,final String value,final Long expireSeconds){
        redisTemplate.opsForValue().set(key,value,expireSeconds, TimeUnit.SECONDS);
    }

    public void putAllProp(Map<String,Object> properties,String key){
        redisTemplate.opsForHash().putAll(key,properties);
    }

    public Object getHashProp(String key,String val){
            return  redisTemplate.opsForHash().get(key,val);
    }

}
