package com.yootk.lettuce.util;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @Author huang cheng
 * @Date 2020/10/5 0005 20:24
 * @Version 1.0
 */
public class RedisConnectionUtil {
    public static final String REDIS_ADDRES="redis://hellolee@192.168.10.107:6379/0";
    private static final int MAX_IDLE=10;
    private static final int MIX_IDLE=1;
    private static final int MAX_TOTAL=1;
    private static final boolean TEST_ON_BORROW=true;
    private static GenericObjectPool<StatefulRedisConnection<String,String>> pool;
    private static final RedisURI REDIS_URI=RedisURI.create(REDIS_ADDRES);
    private static final RedisClient REDIS_CLIENT = RedisClient.create(REDIS_URI);
    private static final ThreadLocal<StatefulRedisConnection> REDIS_CONNECTION_THREAD_LOCAL = new ThreadLocal<>();
    static{
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(MAX_IDLE);
        config.setMinIdle(MIX_IDLE);
        config.setMaxTotal(MAX_TOTAL);
        config.setTestOnBorrow(TEST_ON_BORROW);
        pool= ConnectionPoolSupport.createGenericObjectPool(()->REDIS_CLIENT.connect(),config);
    }
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
       // StatefulRedisConnection<String,String> connect = REDIS_CLIENT.connect();
        try {
            return pool.borrowObject();
        }catch (Exception e){
            return null;
        }

    }
}
