package com.yootk.util.dbc;

import redis.clients.jedis.Jedis;

/**
 * @Author huang cheng
 * @Date 2020/10/6 0006 11:38
 * @Version 1.0
 */
public class RedisConnectionBase {
    public static final String REDIS_HOST="192.168.10.107";
    public static final int REDIS_PORT=6379;
    public static final String  REDIS_AUTH="hellolee";
    private Jedis jedis;
    public RedisConnectionBase(){
        this.jedis=new Jedis(REDIS_HOST,REDIS_PORT);
        this.jedis.auth(REDIS_AUTH);
    }
    public void close(){
        this.jedis.close();
    }
    public Jedis getConnection(){
        return this.jedis;
    }
}
