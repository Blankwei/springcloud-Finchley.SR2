package com.savoidage.common.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-09 14:12
 * Description: RedisUtils
 */
public class RedisUtils {

    private RedisTemplate redisTemplate;

    public RedisUtils(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    /**
     * 写入缓存
     * @param key 存储键
     * @param value 对应值
     * @return boolean 是否存储成功的标识
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value) {
        boolean result = false;
        if(null == key || null == value) return false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     * @param key 存储键
     * @param value 对应值
     * @return boolean 是否存储成功的标识
     */
    @SuppressWarnings("unchecked")
    public boolean set(String key, Object value, Long expireTime) {
        boolean result = false;
        if(null == key || null == value) return false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            expireTime = null == expireTime ? redisTemplate.getExpire(key, TimeUnit.SECONDS) : expireTime;
            if(expireTime < 0){
                operations.set(key,value);
            }else if(expireTime == 0L){
                redisTemplate.delete(key);
            }else{
                operations.set(key,value,expireTime,TimeUnit.SECONDS);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取缓存
     * @param key 读取指定键
     * @return 读取的数据值
     */
    @SuppressWarnings("unchecked")
    public Object get(String key) {
        if(null==key){
            return null;
        }
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 哈希 添加
     * @param key 存储指定键
     * @param hashKey 存储的哈希键
     * @param value 存储对应的哈希值
     */
    @SuppressWarnings("unchecked")
    public boolean hmSet(String key, Object hashKey, Object value){
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 哈希获取数据
     * @param key 指定键
     * @param hashKey 指定哈希键
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object hmGet(String key, Object hashKey){
        if(null == key || null == hashKey){
            return null;
        }
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }

    /**
     * 删除对应的value
     * @param key 指定键
     */
    @SuppressWarnings("unchecked")
    public void remove(String key) {
        if(null == key){
            return;
        }
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     * @param key 指定键
     * @return 是否存在的标识
     */
    @SuppressWarnings("unchecked")
    public boolean exists(String key) {
        if(null == key){
            return false;
        }
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
