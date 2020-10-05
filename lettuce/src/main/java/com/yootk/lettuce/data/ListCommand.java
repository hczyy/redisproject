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
public class ListCommand {
    public static void main(String[] args) throws Exception{
        RedisAsyncCommands commands = RedisConnectionUtil.getConnection().async();
        commands.flushdb().get();
        commands.lpush("message-queue","hello-a","hello-b","hello-c").get();
        System.out.println("[list队列内容]"+commands.lrange("message-queue",0,-1).get());
        System.out.println("[list队列长度]"+commands.llen("message-queue").get());
        RedisConnectionUtil.close();
    }
}
