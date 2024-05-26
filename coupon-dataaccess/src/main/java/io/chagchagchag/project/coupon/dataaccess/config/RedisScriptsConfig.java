package io.chagchagchag.project.coupon.dataaccess.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.RedisScript;


@Configuration
public class RedisScriptsConfig {
  @Bean
  public RedisScript<String> couponEnqueueScript(){
    ClassPathResource scriptResource = new ClassPathResource("lua/coupon-enqueue-script.lua");
    return RedisScript.of(scriptResource, String.class);
  }
}
