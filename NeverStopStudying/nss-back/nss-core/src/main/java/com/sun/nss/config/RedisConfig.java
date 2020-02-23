package com.sun.nss.config;

import com.sun.nss.common.constants.RedisCacheNames;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


/**
 * redis 配置类，做缓存管理
 * @author zcm
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 缓存管理器，负责数据缓存
     * @return
     */
    @Bean
    public CacheManager cacheManager(){

      return new RedisCacheManager(
              RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
              RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(7))
              .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())),
              this.getRedisCacheConfigurationMap()
      );
    }

    private Map<String,RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        //缓存默认1天
        Map<String,RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>(1);
        redisCacheConfigurationMap.put(RedisCacheNames.ARTICLE, RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())));
        redisCacheConfigurationMap.put(RedisCacheNames.BOOK, RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())));
        redisCacheConfigurationMap.put(RedisCacheNames.BOOKNOTE, RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())));
        return redisCacheConfigurationMap;
    }


    /**
     * 对redis template进行配置
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        //序列化,使用string进行序列化。
        //TODO 等项目完结，使用json进行序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //连接。也可以定制
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * key value 操作
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
