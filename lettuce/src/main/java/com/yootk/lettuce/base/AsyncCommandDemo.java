package com.yootk.lettuce.base;

import com.yootk.lettuce.util.RedisConnectionUtil;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.concurrent.TimeUnit;

/**
 * @Author huang cheng
 * @Date 2020/10/5 0005 20:35
 * @Version 1.0
 */
public class AsyncCommandDemo {
    public static void main(String[] args) throws Exception{
        RedisAsyncCommands commands = RedisConnectionUtil.getConnection().async();
        commands.setex("msg",3,"hello");
        TimeUnit.SECONDS.sleep(1);
        RedisFuture future = commands.get("msg");
        System.out.println(future.get());
        TimeUnit.SECONDS.sleep(3);
        System.out.println(commands.get("msg").get());
        RedisConnectionUtil.close();
    }
}
