package io.chagchagchag.project.coupon.dataaccess.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CaffeineCacheConfig {

    @Bean
    public CacheManager caffeineCacheManager(){
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine
                .newBuilder()
                .expireAfterWrite(Duration.ofSeconds(10))
                .maximumSize(1000);
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

}
