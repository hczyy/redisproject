package com.yootk.lettuce.connect;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;

/**
 * @Author huang cheng
 * @Date 2020/10/2 0002 16:41
 * @Version 1.0
 */
public class RedisServerConnectionDemoC {
    public static final String REDIS_ADDRES="redis://hellolee@192.168.10.107:6379/0";


    public static void main(String[] args) {
        RedisURI redisURI = RedisURI.create(REDIS_ADDRES);
        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection<String,String> connect= redisClient.connect();
        System.out.println("连接实例"+connect);
        connect.close();
        redisClient.shutdown();
        
    }

}
