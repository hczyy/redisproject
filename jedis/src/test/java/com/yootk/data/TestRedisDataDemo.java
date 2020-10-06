package com.yootk.data;

import com.yootk.util.dbc.RedisConnectionUtil;
import org.junit.Test;
import redis.clients.jedis.*;
import redis.clients.jedis.params.GeoRadiusParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author huang cheng
 * @Date 2020/10/6 0006 11:47
 * @Version 1.0
 */
public class TestRedisDataDemo {
    public static Jedis jedis = null;
    static{
        RedisConnectionUtil connection= new RedisConnectionUtil();
         jedis = connection.getConnection();
    }
    @Test
    public void testStringData()throws Exception{
        jedis.set("mldn","java");
        jedis.setex("mldn-message",3,"helloword");
        TimeUnit.SECONDS.sleep(4);
        System.out.println(jedis.get("mldn"));
        System.out.println(jedis.get("mldn-messgae"));
    }
    @Test
    public void testHashData()throws Exception{
        jedis.hset("user-mldn","name","zyy");
        jedis.hset("user-mldn","age", String.valueOf(18));
        jedis.hset("user-mldn","sex","女");
        System.out.println(jedis.hget("user-mldn","name"));
        System.out.println(jedis.hget("user-mldn","age"));
        System.out.println(jedis.hget("user-mldn","sex"));
    }
    @Test
    public void testListData()throws Exception{
        jedis.flushDB();
        jedis.lpush("user-lee","zyy","lyn","jc");
        jedis.rpush("user-lee","hx","xhy");
        System.out.println(jedis.lpop("user-lee"));
        System.out.println(jedis.lpop("user-lee"));
        System.out.println(jedis.rpop("user-lee"));
        System.out.println(jedis.rpop("user-lee"));
    }
    @Test
    public void testListDataGet()throws Exception{
        jedis.flushDB();
        jedis.lpush("mldn-lee","zyy","lyn","jc");
        jedis.rpush("mldn-lee","hx","xhy");
        List<String> all= jedis.lrange("mldn-lee",0,-1);
        all.forEach((data)->{
            System.out.println(data+"、");
        });
    }
    @Test
    public void testSetDataGet()throws Exception{
        jedis.flushDB();
        jedis.sadd("user-lee","a","d","c","d");
        jedis.sadd("user-hello","a","d","x","y");
        Set<String> all= jedis.sinter("user-lee","user-hello");
        all.forEach((data)->{
            System.out.println(data+"、");
        });
    }
    @Test
    public void testZsetData()throws Exception{
        jedis.flushDB();
        Map<String,Double> map = new HashMap<>();
        map.put("pid-1",1.0);
        map.put("pid-2",8.0);
        map.put("pid-3",6.0);
        map.put("pid-4",3.0);
        jedis.zadd("user-key",map);
        Set<Tuple> all= jedis.zrangeByScoreWithScores("user-key",1.0,7.0);
        all.forEach((data)->{
            System.out.println(data.getElement()+"----"+data.getScore());
        });
    }
    @Test
    public void testGEOData() throws Exception {
        jedis.flushDB() ;									// 清空数据库
        Map<String, GeoCoordinate> pointsMap = new HashMap<String,GeoCoordinate>() ;	// 保存坐标
        pointsMap.put("天安门",new GeoCoordinate(116.403963, 39.915119)) ;	// 添加坐标
        pointsMap.put("王府井",new GeoCoordinate(116.417876, 39.915411)) ;	// 添加坐标
        pointsMap.put("前门大街",new GeoCoordinate(116.404354, 39.904748)) ;	// 添加坐标
        jedis.geoadd("point", pointsMap) ;					// 保存坐标信息
        // 查找距离当前坐标周围1000M的建筑物信息
        List<GeoRadiusResponse> georadius = jedis.georadius("point", 116.415901, 39.914805, 1000, GeoUnit.M, GeoRadiusParam.geoRadiusParam().withDist());
        georadius.forEach((geoData)->{
            System.out.println("建筑物名称：" + geoData.getMemberByString() + "、距离：" + geoData.getDistance());
        });													// 迭代输出
    }
}
