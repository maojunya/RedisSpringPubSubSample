package com.app.controller;

import com.app.model.User;
import com.app.nosql.RedisGetterSetterUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ozgur on 02.04.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class RedisGetterSetterUtilTests {

    @Autowired
    private RedisGetterSetterUtil redisGetterSetterUtil;

    @Test
    public void redisTest(){
        redisGetterSetterUtil.setValue("foo", "bar");
        System.out.println(redisGetterSetterUtil.getValue("foo"));
    }

    @Test
    public void redisExpireGetSetterTest() throws InterruptedException {
        redisGetterSetterUtil.setValue("ozgur","demirel",10L);
        System.out.println(redisGetterSetterUtil.getValue("ozgur"));
        Thread.sleep(9000);
        System.out.println(redisGetterSetterUtil.getValue("ozgur"));
        Thread.sleep(2000);
        System.out.println(redisGetterSetterUtil.getValue("ozgur"));
    }

    @Test
    public void testRedisPutAll(){
        User user =new User();
        user.setUserId(100L);
        user.setUsername("username");
        user.setPassword("password");
        Map<String,Object> prop=new HashMap<String, Object>();
        prop.put("userId",user.getUserId());
        prop.put("userName",user.getUsername());
        prop.put("passWord",user.getPassword());
        redisGetterSetterUtil.putAllProp(prop,String.format("user%s",String.valueOf(user.getUserId())));
        String username = (String) redisGetterSetterUtil.getHashProp(String.format("user%s", String.valueOf(user.getUserId())), "userName");
        System.out.println(username);
    }

}
