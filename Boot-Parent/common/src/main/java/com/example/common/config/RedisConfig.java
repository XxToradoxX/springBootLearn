package com.example.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfig {
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        log.info("开始创建redis模板对象:value=={}",redisConnectionFactory);

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();

        // 设置redis连接工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 设置 redis key 的序列化器，可以解决乱码问题
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // 设置 redis 值的序列化器，可以解决乱码问题
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));

        return redisTemplate;
    }
}
