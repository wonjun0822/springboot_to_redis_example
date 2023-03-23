package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    // @Value("${spring.redis.password}")
    // private int password;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();

        redisConfiguration.setHostName(host);
        redisConfiguration.setPort(port);
        // redisConfiguration.setPassword(password);

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisConfiguration);

        return lettuceConnectionFactory;
    }

    // redis에 session 저장 될때 Serializer 되서 저장 되도록 해준다
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean(name = "cacheManager")
    // Redis Cache Manager 설정
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues() // null value 캐시 하지 않음
                .entryTtl(Duration.ofSeconds(30)) // 캐시의 기본 유효시간 설정
                .computePrefixWith(CacheKeyPrefix.simple())
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())); // redis 캐시 데이터 저장방식을 StringSeriallizer로 지정

        // 캐시별 유효시간 설정필요할 시 아래 설정 참고
        // Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        // cacheConfigurations
        //        .put(Cacheable cacheNames, RedisCacheConfiguration.defaultCacheConfig()
        //        .entryTtl(Duration.ofSeconds(30)));

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                .cacheDefaults(configuration)
                //.withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }

//    @Primary
//    @Bean
//    public RedisTemplate<String, String> redisTemplate() {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//
//        return redisTemplate;
//    }
}
