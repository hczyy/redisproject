package com.yootk.lettuce.pool;

import com.yootk.lettuce.util.RedisConnectionUtil;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @Author huang cheng
 * @Date 2020/10/5 0005 21:38
 * @Version 1.0
 */
public class RedisConfigPool {
    private static final int MAX_IDLE=10;
    private static final int MIX_IDLE=1;
    private static final int MAX_TOTAL=1;
    private static final boolean TEST_ON_BORROW=true;

    public static void main(String[] args) throws Exception {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(MAX_IDLE);
        config.setMinIdle(MIX_IDLE);
        config.setMaxTotal(MAX_TOTAL);
        config.setTestOnBorrow(TEST_ON_BORROW);
        GenericObjectPool<StatefulRedisConnection<String,String>> pool= ConnectionPoolSupport.createGenericObjectPool(()->
            RedisConnectionUtil.getConnection(),config);
        for (int x = 0;x<10;x++){
            StatefulRedisConnection<String,String> connection= pool.borrowObject();
            System.out.println(connection);
            System.out.println(connection.sync().ping());
            connection.close();
        }
    }
}
