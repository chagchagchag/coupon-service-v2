package io.chagchagchag.project.coupon.api.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    RedissonClient redissonClient(){
        Config config = new Config();

        String address = "redis://%s:%s".formatted(redisHost, redisPort);

        config.useSingleServer().setAddress(address);
        return Redisson.create();
    }

}
