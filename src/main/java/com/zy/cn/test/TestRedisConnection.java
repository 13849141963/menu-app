package com.zy.cn.test;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.LinkedHashSet;
import java.util.Set;

public class TestRedisConnection {

    Jedis jedis =null;
    @Before
    public void testAfter(){
        jedis = new Jedis("192.168.1.21",6379);
    }
    //测试连接redis
    @Test
    public void testConnectionRedis(){

        String set = jedis.set("ppp", "用户");
        String ppp = jedis.get("ppp");
        System.out.println(ppp);
    }

    //
    //redis实现简单的消息队列
    @Test
    public void test(){
        jedis.lpush("task-queue", "123");//装入队列
        jedis.lpush("task-queue", "456");//装入队列
        //获取队列消息
        System.out.println(jedis.rpoplpush("task-queue", "tmp-queue"));
        //获取队列消息
        System.out.println(jedis.rpoplpush("task-queue", "tmp-queue"));
        // 将本次任务从暂存队列"tmp-queue"中清除
        jedis.rpop("tmp-queue");
        System.out.println("处理成功，被清除");
        //获取队列消息
        System.out.println(jedis.rpoplpush("task-queue", "tmp-queue"));
        //获取队列消息
        System.out.println(jedis.rpoplpush("task-queue", "tmp-queue"));
    }

    //
    @Test
    public void test1(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(1);
        // 最大空闲数
        poolConfig.setMaxIdle(1);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis(1000);
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.1.21", 7000));
        nodes.add(new HostAndPort("192.168.1.21", 7001));
        nodes.add(new HostAndPort("192.168.1.21", 7002));
        nodes.add(new HostAndPort("192.168.1.21", 7003));
        nodes.add(new HostAndPort("192.168.1.21", 7004));
        nodes.add(new HostAndPort("192.168.1.21", 7005));
        nodes.add(new HostAndPort("192.168.1.21", 7006));
        JedisCluster cluster = new JedisCluster(nodes, poolConfig);
        String set = cluster.set("new", "new");
        String aNew = cluster.get("new");
        System.out.println(aNew);
    }





}
