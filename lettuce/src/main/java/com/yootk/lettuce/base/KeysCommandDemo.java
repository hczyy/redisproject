package com.yootk.lettuce.base;

import com.yootk.lettuce.util.RedisConnectionUtil;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.concurrent.TimeUnit;

/**
 * @Author huang cheng
 * @Date 2020/10/5 0005 20:35
 * @Version 1.0
 */
public class KeysCommandDemo {
    public static void main(String[] args) throws Exception{
        RedisAsyncCommands commands = RedisConnectionUtil.getConnection().async();
        RedisFuture  future = commands.keys("*");
        System.out.println(future.get());
        RedisConnectionUtil.close();
    }
}
