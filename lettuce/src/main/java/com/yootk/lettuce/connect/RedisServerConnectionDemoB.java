package com.yootk.lettuce.connect;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;

/**
 * @Author huang cheng
 * @Date 2020/10/2 0002 16:41
 * @Version 1.0
 */
public class RedisServerConnectionDemoB {
    public static final String REDIS_HOST="192.168.10.107";
    public static final int REDIS_PORT=6379;
    public static final String  REDIS_AUTH="hellolee";
    public static final int REDIS_DATABASE_INDEX=0;

    public static void main(String[] args) {
        RedisURI redisURI = RedisURI.Builder.redis(REDIS_HOST).withPort(REDIS_PORT)
                .withPassword(REDIS_AUTH)
                .withDatabase(REDIS_DATABASE_INDEX).build();
        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection<String,String> connect= redisClient.connect();
        System.out.println("连接实例"+connect);
        connect.close();
        redisClient.shutdown();
        
    }

}
