package com.yootk.lettuce.util;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;

/**
 * @Author huang cheng
 * @Date 2020/10/5 0005 20:24
 * @Version 1.0
 */
public class RedisConnectionUtilBase {
    public static final String REDIS_ADDRES="redis://hellolee@192.168.10.107:6379/0";
    private static final RedisURI REDIS_URI=RedisURI.create(REDIS_ADDRES);
    private static final RedisClient REDIS_CLIENT = RedisClient.create(REDIS_URI);
    private static final ThreadLocal<StatefulRedisConnection> REDIS_CONNECTION_THREAD_LOCAL = new ThreadLocal<>();
    public static StatefulRedisConnection getConnection(){
        StatefulRedisConnection<String,String> connection = REDIS_CONNECTION_THREAD_LOCAL.get();
        if(connection == null){
            connection=build();
            REDIS_CONNECTION_THREAD_LOCAL.set(connection);
        }
        return  connection;
    }
    public static void close(){
        StatefulRedisConnection<String,String> connection = REDIS_CONNECTION_THREAD_LOCAL.get();
        if(connection != null){
            connection.close();
            REDIS_CONNECTION_THREAD_LOCAL.remove();
        }
    }
    private static StatefulRedisConnection build(){
        StatefulRedisConnection<String,String> connect = REDIS_CLIENT.connect();
        return connect;
    }
}
