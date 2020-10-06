package com.yootk.util.dbc;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author huang cheng
 * @Date 2020/10/6 0006 11:38
 * @Version 1.0
 */
public class RedisConnectionUtil {
    private static final String REDIS_HOST = "192.168.10.107" ;	// 主机名称
    private static final int REDIS_PORT = 6379 ;				// 端口名称
    private static final String REDIS_AUTH = "hellolee" ;		// 认证信息
    private static final int TIMEOUT = 2000 ; 					// 连接超时时间
    private static final int MAX_TOTAL = 200 ;					// 最多允许200个的连接
    private static final int MAX_IDLE = 20 ;					// 没有访问时的最小维持数量
    private static final int MAX_WAIT_MILLIS = 1000 ;			// 最大等待时间
    private static final boolean TEST_ON_BORROW = true ;		// 是否要进行连接测试
    private JedisPool pool = null ;								// 连接池对象
    public RedisConnectionUtil() {								// 构造方法连接数据库
        // 如果要想使用连接池进行控制，那么一定需要进行连接池的相关配置
        JedisPoolConfig config = new JedisPoolConfig() ;		// 进行连接池配置
        config.setMaxTotal(MAX_TOTAL);							// 最大连接数
        config.setMaxIdle(MAX_IDLE);							// 最小维持连接数
        config.setMaxWaitMillis(MAX_WAIT_MILLIS);				// 最大等待时间
        config.setTestOnBorrow(TEST_ON_BORROW); 				// 测试通过后返回可用连接
        this.pool = new JedisPool(config,REDIS_HOST,REDIS_PORT,TIMEOUT,REDIS_AUTH) ;
    }
    public Jedis getConnection() {
        return this.pool.getResource() ; 						// 连接池获取连接
    }
    public void close() {
        this.pool.close(); 										// 连接池关闭
    }

}