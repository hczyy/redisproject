package com.yootk.lettuce.data;

import com.yootk.lettuce.util.RedisConnectionUtil;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author huang cheng
 * @Date 2020/10/5 0005 20:35
 * @Version 1.0
 */
public class HashCommand {
    public static void main(String[] args) throws Exception{
        RedisAsyncCommands commands = RedisConnectionUtil.getConnection().async();
        commands.hset("member-lee","name","小李老师").get();
        Map<String,String> map = new HashMap<>();
        map.put("age",String.valueOf(18));
        map.put("salay",String.valueOf(1.1));
        commands.hmset("member-lee",map).get();
        System.out.println(commands.hgetall("member-lee").get());
        RedisConnectionUtil.close();
    }
}
