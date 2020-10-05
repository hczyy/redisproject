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
public class ZsetCommand {
    public static void main(String[] args) throws Exception{
        RedisAsyncCommands commands = RedisConnectionUtil.getConnection().async();
       commands.flushdb().get();
       commands.zadd("hotword",12.0,"helloword");
       commands.zadd("hotword",22.0,"小李老师");
       commands.zadd("hotword",1.1,"zyy");
       commands.zadd("hotword",5.0,"zyy");
        System.out.println("升序排列"+commands.zrange("hotword",0,-1).get());
        System.out.println("升序排列"+commands.zrevrange("hotword",0,-1).get());
        System.out.println("升序排列"+commands.zrangeWithScores("hotword",0,-1).get());
        System.out.println("升序排列"+commands.zrevrangeWithScores("hotword",0,-1).get());
        RedisConnectionUtil.close();
    }
}
