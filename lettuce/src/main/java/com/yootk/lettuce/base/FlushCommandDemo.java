package com.yootk.lettuce.base;

import com.yootk.lettuce.util.RedisConnectionUtil;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

/**
 * @Author huang cheng
 * @Date 2020/10/5 0005 20:35
 * @Version 1.0
 */
public class FlushCommandDemo {
    public static void main(String[] args) throws Exception{
        RedisAsyncCommands commands = RedisConnectionUtil.getConnection().async();
        commands.flushall();
        RedisConnectionUtil.close();
    }
}
