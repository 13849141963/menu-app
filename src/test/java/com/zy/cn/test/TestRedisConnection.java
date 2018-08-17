package com.zy.cn.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-data-redis.xml")
public class TestRedisConnection {


    @Autowired
    private RedisTemplate redisTemplate;

    //
    @Test
    public void testConnection(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("key","数据123");
        Object key = valueOperations.get("key");
        System.out.println(key);
    }

    //
    @Test
    public void testoireuwoiuroi(){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("keyss","1","权限");
        Boolean keyss = hashOperations.hasKey("keyss", "1");
        System.out.println(keyss);
    }







}
