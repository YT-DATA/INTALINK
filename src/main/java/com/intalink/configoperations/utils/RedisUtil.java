package com.intalink.configoperations.utils;

import com.intalink.configoperations.service.dataTableRelationBasic.impl.IkRpDataTableRelationBasicServiceImpl;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.List;
import java.util.Set;

public class RedisUtil {
    private static JedisPool jedisPool;

    private static final String FATHER_PREFIX = "father:";
    private static final String CHILD_PREFIX = "child:";

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);

        // 添加数据库索引参数，0是Redis的默认数据库
        int databaseIndex = 0;
        jedisPool = new JedisPool(poolConfig, "39.106.28.179", 6379, Protocol.DEFAULT_TIMEOUT, "Liuzong123456.", databaseIndex);
    }
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    // 检查是否存在类型为 list 的节点  如没有则创建个空节点
    public boolean isListExists(String key) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            boolean isListExists = "list".equals(jedis.type(key));
            closeJedisCon(jedis);
            return isListExists;
        }
    }

    //创建一个空的list节点
    public void createListNode(String key) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            // 向列表添加一个临时元素
            jedis.rpush(key, "temp");

            // 删除临时元素
            jedis.lpop(key);// 移除初始值，保持 list 为空
            closeJedisCon(jedis);
        }
    }


    // 添加节点
    public void addListNode(String parentKey, String key) {
        try (Jedis jedis = RedisUtil.getJedis()) {


            createListNode(key);// 创建list节点
            String fullFatherKey = FATHER_PREFIX + parentKey;

            if (!jedis.exists(fullFatherKey)) {
                jedis.lpush(fullFatherKey, "");
                jedis.ltrim(fullFatherKey, 1, 0);
            }


            jedis.rpush(fullFatherKey, key);
            if (parentKey != null) {
                jedis.sadd(parentKey + ":children", key);
            }
            closeJedisCon(jedis);
        }
    }

    // 删除节点
    public void deleteNode(String key) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            Set<String> children = jedis.smembers(key + ":children");
            if (children != null) {
                for (String child : children) {
                    deleteNode(child);
                }
            }
            jedis.del(key + ":list"); // 删除列表
            jedis.del(key + ":children");
            closeJedisCon(jedis);
        }
    }

    // 增加最后一层节点中的列表元素
    public static void addToList(String key, String value) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            jedis.rpush(key + ":list", value); // 添加列表元素
            closeJedisCon(jedis);
        }
    }

    // 删除最后一层节点中的列表元素
    public void removeFromList(String key, String value) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            jedis.lrem(key + ":list", 1, value); // 删除列表中的元素
            closeJedisCon(jedis);
        }
    }


    // 根据第三层节点值查找节点key
    public String findKeyByValue(String value, String parentKey) {
        try (Jedis jedis = jedisPool.getResource()) {
            String fullFatherKey = FATHER_PREFIX + parentKey;
            List<String> children = jedis.lrange(fullFatherKey, 0, -1);

            for (String childKey : children) {
                List<String> childValues = jedis.lrange(childKey, 0, -1);
                if (childValues.contains(value)) {
                    return childKey;
                }
            }
            closeJedisCon(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Redis列表中的所有数据
     *
     * @param listKey 列表的键
     * @return 列表中所有元素的List
     */
    public List<String> fetchAllFromList(String listKey) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            // 使用LRANGE命令获取所有元素
            List<String> values = jedis.lrange(listKey, 0, -1);
            closeJedisCon(jedis);
            return values;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 批量向Redis列表添加数据
     *
     * @param listKey 列表的键
     * @param values  要添加的值列表
     */
    public static void batchInsertToList(String listKey, List<String> values) {
        try (Jedis jedis = jedisPool.getResource()) {
            // 将List<String> 转为 String[]
            String[] valuesArray = values.toArray(new String[0]);
            // 使用RPUSH添加数据
            jedis.rpush(listKey, valuesArray);
            closeJedisCon(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭jedis链接
     *
     * @param jedis
     */
    public static void closeJedisCon(Jedis jedis) {
        jedis.close();
    }


    public static void removeRedisNode(String key){
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
            closeJedisCon(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查Redis列表是否包含指定的值
     *
     * @param listKey 列表的键
     * @param value   要检查的值
     * @return 如果列表包含该值，则返回true；否则返回false
     */
    public static boolean isValueInList(String listKey, String value) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            // 获取列表的所有元素
            List<String> list = jedis.lrange(listKey, 0, -1);
            closeJedisCon(jedis);
            // 检查列表是否包含指定的值
            return list.contains(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据rediskey的类型获取数据
     */
    public static String getRedisDataByRedisKey(String redisKey) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            // 检查数据类型
            String type = jedis.type(redisKey);
            switch (type) {
                case "string":
                    return jedis.get(redisKey);
                case "list":
                    return jedis.lrange(redisKey, 0, -1).toString();
                case "set":
                    return jedis.smembers(redisKey).toString();
                case "zset":
                    return jedis.zrange(redisKey, 0, -1).toString();
                case "hash":
                    return jedis.hgetAll(redisKey).toString();
                default:
                    return "Unsupported data type";
            }

        }
    }


    /**
     * 判断新数据源值是否存在
     */
    public static boolean getSismember(String string, String redisKey) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            boolean type = jedis.sismember(string, redisKey);
            closeJedisCon(jedis);
            return type;
        }
    }
    /**
     *  添加set值
     */
    public static void setValue(String redisKey, String redisValue) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            jedis.set(redisKey, redisValue);
            closeJedisCon(jedis);
        }
    }
    /**
     *  srem  删除数据
     */
    public static void sremValue(String redisKey, String redisValue) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            jedis.srem(redisKey, redisValue);
            closeJedisCon(jedis);
        }
    }
    /**
     *  smembers  根据获取所有数据表名称
     */
    public static Set<String> getSmembers(String redisKey) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            Set<String> smembers = jedis.smembers(redisKey);
            closeJedisCon(jedis);
            return smembers;
        }
    }
    /**
     *  del  根据key直接删除
     */
    public static void delByRedisKey(String redisKey) {
        try (Jedis jedis = RedisUtil.getJedis()) {
            jedis.del(redisKey);
            closeJedisCon(jedis);
        }
    }

}
