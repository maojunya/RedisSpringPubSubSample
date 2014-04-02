package com.app.controller;

import com.app.nosql.RedisPublishMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ozgur on 02.04.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class RedisQueueTests {

    @Autowired
    private RedisPublishMessage redisPublishMessage;

    @Test
    public void redisPublishMessageTest(){
       for (int i=0;i<1200;i++){
           redisPublishMessage.publish("messaj"+i);
       }
    }
}
