package com.yootk.lettuce.data;

import com.yootk.lettuce.util.RedisConnectionUtil;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author huang cheng
 * @Date 2020/10/5 0005 20:35
 * @Version 1.0
 */
public class GEOCommand {
    public static void main(String[] args) throws Exception{
        RedisAsyncCommands commands = RedisConnectionUtil.getConnection().async();
       commands.flushdb().get();
       commands.geoadd("point",116.403847,39.915526,"天安门");
        commands.geoadd("point",116.418668,39.922232,"王府井 ");
        commands.geoadd("point",116.404588,39.905524,"前门");
        RedisFuture point = commands.georadius("point", 116.415901, 39.914805, 2000, GeoArgs.Unit.m);
        System.out.println(point.get());

        RedisConnectionUtil.close();
    }
}
