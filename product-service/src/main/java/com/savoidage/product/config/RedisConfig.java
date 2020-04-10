package com.savoidage.product.config;

import com.savoidage.common.redis.FastJsonRedisSerializer;
import com.savoidage.common.redis.RedisUtils;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-09 14:33
 * Description: RedisConfig
 */
@Configuration
// 启动缓存
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisUtils getRedisUtils(LettuceConnectionFactory connectionFactory){
        RedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);
        return new RedisUtils(redisTemplate);
    }

    // 获取redisTemplate
    @Bean
    public RedisTemplate<?,?> getRedisTemplate(LettuceConnectionFactory connectionFactory){
        RedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);
        return redisTemplate;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // fastjson 序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);

        // value使用fastJsonRedisSerializer序列化
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // key使用StringRedisSerializer序列化
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }
}
