package com.yootk.test;

import com.yootk.util.dbc.RedisConnectionUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Author huang cheng
 * @Date 2020/10/6 0006 11:42
 * @Version 1.0
 */
public class TestRedisConnection {
    @Test
    public void testConnection(){
        RedisConnectionUtil connection= new RedisConnectionUtil();
        Jedis jedis = connection.getConnection();
        System.out.println(jedis);
        jedis.close();
    }
}
