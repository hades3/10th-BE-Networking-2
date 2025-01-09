package cotato.backend.common.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfig {

	@Bean
	public RedisCacheManager redisCacheManager(RedisConnectionFactory cf) {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
			.serializeKeysWith(
				RedisSerializationContext
				.SerializationPair
				.fromSerializer(new StringRedisSerializer())
			)
			.serializeValuesWith(
				RedisSerializationContext
					.SerializationPair
					.fromSerializer(new GenericJackson2JsonRedisSerializer())
			)
			.entryTtl(
				Duration.ofMinutes(3L)
			)
			.disableCachingNullValues();

		return RedisCacheManager
			.RedisCacheManagerBuilder
			.fromConnectionFactory(cf)
			.cacheDefaults(redisCacheConfiguration)
			.build();
	}

}
