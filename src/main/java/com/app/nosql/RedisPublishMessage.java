package com.app.nosql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ozgur on 02.04.2014.
 */
public class RedisPublishMessage {

    @Autowired
    private RedisTemplate<String,Object> template;

    private final AtomicLong counter=new AtomicLong(0);

    @Scheduled(fixedDelay = 50000)
    public void publish(final String message){
        template.convertAndSend("queue",message +" "+counter.incrementAndGet()+" "+Thread.currentThread().getName()+" "+new Date().toString());
    }

}
