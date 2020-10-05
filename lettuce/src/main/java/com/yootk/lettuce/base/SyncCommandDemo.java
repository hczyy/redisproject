package com.yootk.lettuce.base;

import com.yootk.lettuce.util.RedisConnectionUtil;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @Author huang cheng
 * @Date 2020/10/5 0005 20:35
 * @Version 1.0
 */
public class SyncCommandDemo {
    public static void main(String[] args) {
        RedisCommands commands = RedisConnectionUtil.getConnection().sync();
        commands.set("hc","zyy");
        System.out.println(commands.get("hc"));
        RedisConnectionUtil.close();
    }
}
