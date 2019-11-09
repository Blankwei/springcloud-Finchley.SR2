package com.savoidage.common.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-09 14:42
 * Description: FastJsonRedisSerializer
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    private final byte[] bytes = new byte[0];

    private Class<T> tClass;

    public FastJsonRedisSerializer(Class<T> tClass){
        super();
        this.tClass = tClass;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if(null == t){
            return bytes;
        }
        return JSON.toJSONString(t,SerializerFeature.WriteClassName).getBytes(UTF8_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if(null == bytes || bytes.length <= 0){
            return null;
        }
        String s = new String(bytes,UTF8_CHARSET);
        return JSON.parseObject(s,tClass);
    }
}
