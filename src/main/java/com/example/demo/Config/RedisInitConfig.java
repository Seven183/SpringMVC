package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @author siguiqiang
 * @create 2019/3/15
 */
@Configuration
public class RedisInitConfig {

    // 任务池
    private ThreadPoolTaskScheduler taskScheduler = null;

    // RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private RedisConnectionFactory connectionFactory = null;

    // Redis消息监听器
    @Autowired
    private MessageListener redisMsgListener = null;

    // 设置RedisTemplate的序列化器
    private void initRedisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.opsForValue().set("key1","value1");
        redisTemplate.opsForHash().put("hash","field","fkdfksd");
    }

    // 自定义初始化方法
    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    @Bean(name = "redisCacheManager" )
    public RedisCacheManager initRedisCacheManager() {
        // Redis加锁的写入器
        RedisCacheWriter writer= RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        // 启动Redis缓存的默认设置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // 设置JDK序列化器
       // config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));

        // 禁用前缀
        config = config.disableKeyPrefix();
        //设置10分钟超时
        config = config.entryTtl(Duration.ofMinutes(2));
        // 创建缓Redis存管理器
        RedisCacheManager redisCacheManager = new RedisCacheManager(writer, config);
        return redisCacheManager;
    }

	/**
	 * 创建任务池，运行线程等待处理Redis的消息
	 *
	 * @return
	 */
	@Bean
	public ThreadPoolTaskScheduler initTaskScheduler() {
		if (taskScheduler != null) {
			return taskScheduler;
		}
		taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(20);
		return taskScheduler;
	}

	/**
	 * 定义Redis的监听容器
	 *
	 * @return 监听容器
	 */
	@Bean
	public RedisMessageListenerContainer initRedisContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		// Redis连接工厂
		container.setConnectionFactory(connectionFactory);
		// 设置运行任务池
		container.setTaskExecutor(initTaskScheduler());
		// 定义监听渠道，名称为topic1
		Topic topic = new ChannelTopic("topic1");
		// 使用监听器监听Redis的消息
		container.addMessageListener(redisMsgListener, topic);
		return container;
	}
}
