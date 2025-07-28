package com.github.haolinnj.onlinemall.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@EnableCaching
@Configuration
//启动Redis服务：在cmd Redis对应的文件目录下执行：redis-server.exe redis.windows.conf

public class RedisConfig {

    public static final String REDIS_KEY_DATABASE="mall";

    @Bean
    public RedisTemplate<String, Object> redisTemplate (RedisConnectionFactory redisConnectionFactory){
        //调用同类中的方法 redisSerializer()，获取一个配置好的 JSON 序列化器, 用于后续将 Java 对象序列化成 Redis 可以识别的格式
        RedisSerializer<Object> serializer = redisSerializer();
        //创建一个 RedisTemplate 实例，是操作 Redis 的主工具类
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //设置连接 Redis 的工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置Redis中key的序列化方式为字符串String
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置 Redis 中 value 的序列化方式为自定义的 JSON 序列化器
        redisTemplate.setValueSerializer(serializer);
        //设置Hash类型中key的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //设置Hash类型中value的序列化方式 （自定义JSON序列化器）
        redisTemplate.setHashValueSerializer(serializer);
        //初始化方法，在 Spring 中常见，确保设置的属性（比如连接工厂、序列化器）在模板中生效
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean //Spring 注解，表示这个方法会返回一个需要被容器托管的 Bean，也就是说 redisSerializer() 返回的对象会被注入到其他地方
    public RedisSerializer<Object> redisSerializer(){
        //ObjectMapper 是 Jackson 的核心类，专门负责对象与 JSON 之间的转换
        ObjectMapper objectMapper = new ObjectMapper();

        //PropertyAccessor.ALL：对所有类型的属性（包括字段、getter/setter）都应用这个规则。
        //JsonAutoDetect.Visibility.ANY：表示无论字段是 private 还是 public，都能被序列化/反序列化。
        //总之：把所有属性暴露给 Jackson，确保不会漏掉
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType(Object.class) // 或替换为你自己的包路径
                .build();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

        //把上面配置好的 ObjectMapper 应用到serializer 上，使其使用 JSON 方式序列化对象
        //Spring 提供的 Jackson 序列化器，用于将 Java 对象序列化成 JSON 存入 Redis，反之也能反序列化回来
        Jackson2JsonRedisSerializer<Object> serializer =
                new Jackson2JsonRedisSerializer<>(objectMapper,Object.class);

        return serializer;
    }

    @Bean
    public RedisCacheManager redisCacheManager (RedisConnectionFactory redisConnectionFactory){
        //RedisCacheWriter：用于实际执行读写 Redis 缓存的组件。
        //nonLockingRedisCacheWriter(...)：表示不使用分布式锁，即并发时不加锁（性能较高，适合一般场景）。
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //创建一个默认的 Redis 缓存配置对象，里面包含了默认的 key/value 序列化方式、TTL（过期时间）、前缀等
        //.serializeValuesWith(...)：表示要自定义缓存中 value 的序列化器
        //RedisSerializationContext.SerializationPair.fromSerializer(...)：构造一个适配的序列化器对（用于缓存 value）
        //redisSerializer()：自定义的 JSON 序列化器方法（一般返回 Jackson 序列化器）
        //.entryTtl(...)：设置缓存的默认 TTL（Time To Live）过期时间
        //Duration.ofDays(1)：表示每个缓存键值对在 Redis 中存活 1 天（24 小时），之后自动过期
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer()))
                .entryTtl(Duration.ofDays(1));
        return new RedisCacheManager(redisCacheWriter,redisCacheConfiguration);

    }
}
