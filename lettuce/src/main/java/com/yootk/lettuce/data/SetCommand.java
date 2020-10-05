package com.yootk.lettuce.data;

import com.yootk.lettuce.util.RedisConnectionUtil;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author huang cheng
 * @Date 2020/10/5 0005 20:35
 * @Version 1.0
 */
public class SetCommand {
    public static void main(String[] args) throws Exception{
        RedisAsyncCommands commands = RedisConnectionUtil.getConnection().async();
        commands.flushdb().get();
        commands.sadd("friends-lee","a","b","c","d","x","y","z");
        commands.sadd("friends-black","a","b","h","j");
        System.out.println("交集运算"+commands.sinter("friends-lee","friends-black").get());
        System.out.println("差集运算"+commands.sdiff("friends-lee","friends-black").get());
        System.out.println("并集运算"+commands.sunion("friends-lee","friends-black").get());
        RedisConnectionUtil.close();
    }
}
