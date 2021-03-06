package com.example.demo.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lenovo on 2015/11/21.
 */
@Repository
public class BasicRedis {
    private Logger Loggers = LoggerFactory.getLogger(BasicRedis.class);
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    /**
     * 获取当个对象
     * */
    public <T> T get(String prefix, String key, Class<T> clazz) {
             //生成真正的key
             String realKey  = prefix + key;
             String  str = (String) redisTemplate.opsForValue().get(realKey);
             T t =  stringToBean(str, clazz);
             return t;
    }
    @SuppressWarnings("unchecked")
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if(str == null || str.length() <= 0 || clazz == null) {
             return null;
        }
        if(clazz == int.class || clazz == Integer.class) {
             return (T)Integer.valueOf(str);
        }else if(clazz == String.class) {
             return (T)str;
        }else if(clazz == long.class || clazz == Long.class) {
            return  (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }
    
    /**
     * 给定KEY的对象是否已经存在
     * @param key
     * @return
     */
    public boolean exists(String key){
    	Loggers.info( ">>>> exists ---- is exists key ---- " + key);
        boolean re = true;
        try {
        	re = redisTemplate.hasKey(key);
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
			re = false;
		}
        Loggers.info( "<<<< exists ---- is exists key ---- result: " + re);
        return re;
    }

    /**
     * redis保存key-value数据
     * @param key
     * @param value
     * @return
     */
    public boolean putKV(String key ,String value) {
    	Loggers.info(">>>> putKV ----- key : {}----value : {}", key, value);
        boolean re = true;
        try {
        	redisTemplate.opsForValue().set(key, value);
            re = true;
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
        	re = false;
		}
        Loggers.info("<<<< putKV ----- key : {}----result : {}", key, re);
        return re;
    }

    /**
     * redis保存key-value数据
     * @param key
     * @param value
     * @param seconds 大于0表示为给定 key 设置生存时间，小于等于0表示未设置生存时间
     * @return
     */
    public boolean putKV(String key, String value, int seconds) {
    	Loggers.info(">>>> putKV ---- life time: " + seconds + " -------key : {} ---", key);
        boolean re = true;
        try {
        	redisTemplate.opsForValue().set(key, value);
            if(seconds > 0) {
            redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
            }
            re = true;
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
		} 
        Loggers.info("<<<< putKV ----- key : {}----result : {}", key, re);
        return re;
    }

    /**
     * redis 将 key 的值设为 value ，当且仅当 key 不存在。若给定的 key 已经存在，则不做任何动作。
     * @param key
     * @param value
     * @return
     */
    public boolean putNxKV(String key ,String value) {
    	Loggers.info(">>>> putNxKV ----- key : " + key);
        boolean re = true;
        try {
        	re = redisTemplate.opsForValue().setIfAbsent(key, value);
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
		}
        Loggers.info("<<<< putNxKV ----- key : {}----result : {}", key, re);
        return re;
    }

    /**
     * 返回redis 中 key 所关联的字符串值。
     * @param key
     * @return
     */
    public String getKV(String key) {
    	 Loggers.info(">>>> getKV ----- key : " + key);
         String re = null;
         try {
         	re = (String) redisTemplate.opsForValue().get(key);
         }catch (Exception e) {
         	Loggers.error("error :{}", e);
 		}
         Loggers.info("<<<< getKV ----- key : {}----result : {}", key, "success!");
         return re;
    }

    /**
     * 删除redis中给定的 key 。不存在的 key 会被忽略。
     * @param key
     * @return 删除条数
     */
    public String removeKV(String key) {
    	Loggers.info(">>>> removeKV ----- key : " + key);
        String re = null;
        try {
        	redisTemplate.delete(key);
            re = "1";
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
		}
        Loggers.info("<<<< removeKV ----- key : {}----result : {}", key, re);
        return re;
    }

    /**
     * 删除redis中给定的一个或者多个key 。不存在的 key 会被忽略。
     * @param keys
     * @return 删除条数
     */
    public String removeKV(String... keys) {
    	Loggers.info(">>>> removeKV ----- keys : " + keys);
        String re = null;
        try {
        	List<String> key = Arrays.asList(keys);
        	redisTemplate.delete(key);
            re = keys.length + "";
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
		}
        Loggers.info("<<<< removeKV ----- keys : {}----result : {}", keys, re);
        return re;
    }


    /**
     * 将一个或多个值 value 插入到redis列表 key 的表头
     * @param key
     * @param values
     * @return
     */
    public boolean putList(String key, String... values) {
    	 Loggers.info(">>>> putList ----- key : {} ---- values : {}", key, JsonMapper.toJson(values));
         boolean re = true;
         try {
             for(String data : values){
             	redisTemplate.opsForList().leftPush(key, data);
             }
             re = true;
         }catch (Exception e) {
         	Loggers.error("error :{}", e);
 		}
         Loggers.info("<<<< putList ----- key : {}----result : {}", key, re);
         return re;
    }

    /**
     * 返回redis列表 key 中的第一个元素。不移除。
     * @param key
     * @return
     */
    public String getList(String key) {
    	 Loggers.info(">>>> getList ----- get first value ----- key : {} ", key);
         String re = null;
         try {
         	re = (String) redisTemplate.opsForList().index(key, 0);
         }catch (Exception e) {
         	Loggers.error("error :{}", e);
 		}
         Loggers.info("<<<< getList ----- get first value ----- key : {}----result : {}", key, re);
         return re;
    }

    /**
     * 移除并返回redis列表 key 中的第一个元素
     * @param key
     * @return
     */
    public String popList(String key) {
    	 Loggers.info(">>>> popList ----- pop first value ----- key : {} ", key);
         String re = null;
         try {
         	re = (String) redisTemplate.opsForList().leftPop(key);
         }catch (Exception e) {
         	Loggers.error("error :{}", e);
 		}
         Loggers.info("<<<< popList ----- pop first value ----- key : {}----result : {}", key, re);
         return re;
    }

    /**
     * 将redis哈希表 key 中的域 field 的值设为 value 。
     * @param key
     * @param field
     * @param value
     * @return
     */
    public boolean putMap(String key, String field, String value) {
    	 Loggers.info(">>>> putMap ----- put value to key's field ----- key : " + key + " --- field : " + field + " --- value : " + value);
         boolean re = true;
         try {
             redisTemplate.opsForHash().put(key, field, value);
             re = true;
         }catch (Exception e) {
         	Loggers.error("error :{}", e);
 		}
         Loggers.info("<<<< putMap ----- put value to key's field ----- key : {}----result : {}", key, re);
         return re;
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。 会覆盖哈希表中已存在的域。
     * @param key
     * @param map
     * @return
     */
    public boolean putMap(String key,Map map) {
    	 Loggers.info(">>>> putMap ----- put whole map ----- key : {} --- value : {}", key, JsonMapper.toJson(map));
         boolean re = true;
         try {
             redisTemplate.opsForHash().putAll(key, map);
             re = true;
         }catch (Exception e) {
         	Loggers.error("error :{}", e);
         	re = false;
 		}
         Loggers.info("<<<< putMap ----- put whole map ----- key : {}----result : {}", key, re);
         return re;
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。 会覆盖哈希表中已存在的域。
     * @param key
     * @param map
     * @param seconds 大于0表示为给定 key 设置生存时间，小于等于0表示未设置生存时间
     * @return
     */
    public boolean putMap(String key, Map map, int seconds) {
    	 Loggers.info(">>>> putMap ----- put whole map with lifetime ----- key : {} --- value : {} --- lifetime : " + seconds , key, JsonMapper.toJson(map));
         boolean re = true;
         try {
         	 redisTemplate.opsForHash().putAll(key, map);
             if(seconds > 0) {
              redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
             }
             re = true;
         }catch (Exception e) {
         	Loggers.error("error :{}", e);
 		}
         Loggers.info("<<<< putMap ----- put whole map ----- key : {}----result : {}", key, re);
         return re;
    }

    /**
     * 获取redis哈希表key中的域 field 的值。
     * @param key
     * @param field
     * @return
     */
    public String getMap(String key,String field) {
    	Loggers.info(">>>> getMap ----- get key's field value ----- key : {} --- field : {}", key, field);
        String re = null;
        try {
            re = (String) redisTemplate.opsForHash().get(key, field);
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
		}
        Loggers.info("<<<< getMap ----- get key's field value ----- key : {}----result : {}", key, "success!");
        return re;
    }

    /**
     * 获取redis哈希中多个field的value
     * @param key
     * @param fields
     * @return
     */
    public List<String> getMMap(String key, String... fields) {
    	 Loggers.info(">>>> getMMap ----- get key's many field values ----- key : {} --- fields : {}", key, JsonMapper.toJson(fields));
         List<String> values = new ArrayList<String>();
         try {
             values = redisTemplate.opsForHash().multiGet(key,Arrays.asList(fields));
         }catch (Exception e) {
         	Loggers.error("error :{}", e);
 		}
         Loggers.info("<<<< getMMap ----- get key's many field values ----- key : {} --- result : {}", key, "success!");
         return values;
    }

    /**
     * 获取redis中的整个哈希
     * @param key
     * @return
     */
    public Map<String, String> getMapAll(String key) {
    	 Loggers.info(">>>> getMapAll ----- get whole map ----- key : {} ", key);
         Map<String, String> map = null;
         try {
             map = redisTemplate.opsForHash().entries(key);
             Loggers.info("<<<< getMapAll ----- get whole map ----- key : {} --- result : {}", key, "success!");
         }catch (Exception e) {
         	Loggers.error("error :{}", e);
 		}
         return map.size() == 0 ? null : map;

    }

    /**
     * 为哈希表 key 中的域 field 的值加上增量 value 。
     * 增量也可以为负数，相当于对给定域进行减法操作。
     * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long incredMap(String key,String field,int value ) {
    	Loggers.info(">>>> incredMap ----- increase map field's value ----- key : " + key + " --- field : " + field + " --- value : " + value);
        Long newCount = Long.parseLong("-1");
        try {
            newCount = redisTemplate.opsForHash().increment(key, field, value);
            Loggers.info("<<<< incredMap ----- increase map field's value ----- key : {} --- field : {} --- result : " + newCount, key, field);
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
		}
        return newCount;

    }
    /**
     * 
     * @author lit
     * @desc:
     * @date:  2020年12月25日 下午5:23:39  
     *
     * @param key 
     * @param delta 递增因子
     * @return
     */
    public long incr(String key,long delta) {
        /*if(delta<0) {
           throw new RuntimeException("递增银子需大于0");
        }*/
        return redisTemplate.opsForValue().increment(key, delta);
    }
    
    /**
     * 删除redis中key的field。
     * @param key
     * @param field
     * @return
     */
    public boolean removeMap(String key,String field) {
    	 Loggers.info(">>>> removeMap ----- remove map's field ----- key : {} --- field : {}", key, field);
         Long newCount = (long) 0;
         boolean re = true;
         try {
         	newCount = redisTemplate.opsForHash().delete(key, field);
             re = newCount == 0 ? false : true;
             Loggers.info("<<<< removeMap ----- remove map's field ----- key : {} --- field : {} --- result : " + re, key, field);
         }catch (Exception e) {
         	Loggers.error("error :{}", e);
         	re = false;
 		}
         return re;
    }

    /**
     * 给redis中的多个哈希设置生存时间
     * @param keys
     * @param fields
     * @param value
     * @param seconds
     * @return
     */
    public boolean putMapExiperse(String[] keys,String[] fields,String[] value, int[] seconds) {
    	List r = null;
    	try {
    		r = redisTemplate.executePipelined(new RedisCallback<Object>() {
                @Override
            	 public Object doInRedis(RedisConnection connection) throws DataAccessException {
                     connection.openPipeline();
                    for (int i = 0 ;i < keys.length;i++) {
                    	connection.hSet(keys[i].getBytes(), fields[i].getBytes(), value[i].getBytes());
                    	connection.expire(keys[i].getBytes(), seconds[i]);
                     }
                     return null;
                 }
    		});
		} catch (Exception e) {
			Loggers.error("error :{}", e);
		}
    	 return r == null ? false : true;
    }

    /**
     * 获取有序集合中所有的数据
     * @param key
     * @return
     */
    public Set<String> getSortSet(String key) {
    	Loggers.info(">>>> getSortSet ----- get sort set ----- key : {}", key);
        Set<String> res = null;
        try {
        	 res = redisTemplate.opsForZSet().rangeByScore(key, 0, System.currentTimeMillis());
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
		}
        return res;
    }

    /**
     * 向有序集合中放入数据，优先级按照时间排序
     * @param key
     * @param value
     * @return
     */
    public boolean putSortSet(String key,String value) {
    	Boolean res = false;
        try {
            res = redisTemplate.opsForZSet().add(key, value, System.currentTimeMillis());
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
		}
        return res;
    }

    /**
     * 向有序集合中放入数据，自定义优先级
     * @param key
     * @param value
     * @param proi 优先级
     * @return
     */
    public boolean putSortSet(String key,String value,long proi) {
    	Boolean res = false;
        try {
             res = redisTemplate.opsForZSet().add(key, value, proi);
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
		}
        return res;
    }

    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     * @param key
     * @param values
     * @return
     */
    public long removeSortSet(String key,String... values) {
    	Long remove = 0l;
        try {
        	remove = redisTemplate.opsForZSet().remove(key, values);
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
		}
        return remove;
    }

    /**
     * 设置key的生存时间
     * @param key
     * @param seconds
     * @return
     */
    public boolean setExiperse(String key,int seconds) {
    	 Loggers.info(">>>> setExiperse ----- set key's lifetime ----- key : {}", key);
         boolean res = false;
         try {
         	res = redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
         }catch (Exception e) {
         	Loggers.error("error :{}", e);
 		}
         return res;
    }



    /**
     * 向set中 添加记录，如果已经存在，返回0 否则返回1
     * sadd 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 value 元素将被忽略。
     *
     * @param key
     * @param value
     * @return
     */
    public  long add4Set(String key, String... value) {

        try {
        	Long add = redisTemplate.opsForSet().add(key, value);
        	return add;
        }catch (Exception e){
            Loggers.error("error :{}", e);
        }

        return 0L;
    }

    /**
     * 删除set中 指定的元素
     *
     * @param key
     * @param member
     * @return
     */
    public  long delete4Set( String key, String... member) {

        try {
        	Long remove = redisTemplate.opsForSet().remove(key, member);
            return remove;
        }catch (Exception e){
            Loggers.error("error :{}", e);
        }
        return  -1L;

    }

    /**
     * 返回set中 的元素数量
     * 如果set不存在，返回0
     *
     * @param key
     * @return
     */
    public  long length4Set(String key) {

        try {
        	long length = redisTemplate.opsForSet().size(key);
            return length;
        }catch (Exception e){
            Loggers.error("error :{}", e);
        }
        return  -1L;
    }

    /**
     * 判断元素是否存在
     * @param key
     * @param value
     * @return
     */
    public  boolean ifExist4Set(String key, String value) {
        try {
        	boolean result = redisTemplate.opsForSet().isMember(key, value);
            return result;
        }catch (Exception e){
            Loggers.error("error :{}", e);
        }
        return false;
    }

    /**
     * 获取set 中的所有元素
     *
     * @param key
     * @return
     */
    public  Set<String> getSet4All( String key) {

        try {
        	Set<String> result = redisTemplate.opsForSet().members(key);
            return result;
        }catch (Exception e){
            Loggers.error("error :{}", e);
        }
        return null;
    }



    /**
     * 添加一条记录 如果map_key存在 则更新value
     * hset 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果字段已经存在于哈希表中，旧值将被覆盖
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    @Deprecated
    public  long hashSet(String key, String field, String value) {

        try {
            redisTemplate.opsForHash().put(key, field, value);
            long returnStatus = 1L;
            return returnStatus;
        }catch (Exception e){
            Loggers.error("error :{}", e);
        }
        return -1L;
    }

    /**
     * 批量添加记录
     * hmset 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * 此命令会覆盖哈希表中已存在的域。
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     *
     * @param key
     * @param map
     * @return
     */
    @Deprecated
    public  String hashSetAll( String key, Map<String, String> map) {
        try {
        	redisTemplate.opsForHash().putAll(key, map);
            return "OK";
        }catch (Exception e){
            Loggers.error("error :{}", e);
        }
        return null;
    }

    /**
     * 删除hash中 field对应的值
     * hdel 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略
     *
     * @param key
     * @param field
     * @return
     */
    @Deprecated
    public  long hashDelete(String key, String... field) {

        try {
            Long delete = redisTemplate.opsForHash().delete(key, field);
            return delete;

        } catch (Exception e) {
            Loggers.error("error :{}", e);
        }

        return 0L;
    }


    /**
     * 获取hash中 指定的field的值
     * hmget 返回哈希表 key 中，一个或多个给定域的值。
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
     * 不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
     *
     * @param key
     * @param field
     * @return
     */
    @Deprecated
    public  List<String> hashGet(String key, String... field) {
    	 Loggers.info(">>>> getMMap ----- get key's many field values ----- key : {} --- fields : {}", key, JsonMapper.toJson(field));
         List<String> values = new ArrayList<String>();
         try {
             values = redisTemplate.opsForHash().multiGet(key,Arrays.asList(field));
         }catch (Exception e) {
         	Loggers.error("error :{}", e);
 		}
         Loggers.info("<<<< getMMap ----- get key's many field values ----- key : {} --- result : {}", key, "success!");
         return values;

    }

    /**
     * 获取hash中 所有的field value
     *
     * @param key
     * @return 在返回值里，紧跟每个字段名(field name)之后是字段的值(value)，所以返回值的长度是哈希表大小的两倍。
     */
    public  Map<String, String> hashGetAll(String key) {
    	Loggers.info(">>>> getMapAll ----- get whole map ----- key : {} ", key);
        Map<String, String> map = null;
        try {
            map = redisTemplate.opsForHash().entries(key);
            Loggers.info("<<<< getMapAll ----- get whole map ----- key : {} --- result : {}", key, "success!");
        }catch (Exception e) {
        	Loggers.error("error :{}", e);
		}
        return map.size() == 0 ? null : map;

    }

    /**
     * 判断hash中 指定的field是否存在
     *
     * @param key
     * @param field
     * @return 如果哈希不包含字段或key不存在 返回0，如果哈希包含字段 返回1
     */
    public  boolean hashIfExist( String key, String field) {
        try {
            Boolean hasKey = redisTemplate.opsForHash().hasKey(key, field);
            return hasKey;

        }catch (Exception e){
            Loggers.error("error :{}", e);
        }
        return false;
    }

    /**
     * 获取hash 的size
     * hlen 获取哈希表中字段的数量
     *
     * @param key
     * @return
     */
    public  long hashSize(String key) {
        try {
        	Long size = redisTemplate.opsForHash().size(key);
            return size;
        }catch (Exception e){
            Loggers.error("error :{}", e);
        }
        return 0L;
    }

    /**
     * 将一个或多个值 value 插入到redis列表 key 的表尾
     * @param key
     * @param values
     * @return
     */
    public boolean putRList(String key, String... values) {
    	 boolean re = true;
         try {
             for(String data : values){
             	redisTemplate.opsForList().rightPush(key, data);
             }
             re = true;
         }catch (Exception e) {
         	Loggers.error("error :{}","" + e.getMessage());
 		}
         return re;
    }
    
    public Set<String> getAllKeys(String keys) {
    	Set<String> re = null;
        try {
        	re = redisTemplate.keys(keys);
        }catch (Exception e) {
            Loggers.error("error :{}", e);
		}
        return re;
    }
}