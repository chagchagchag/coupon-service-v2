package io.chagchagchag.project.coupon.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAspectJAutoProxy
@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
public class CouponCoreApplication {
    public static void main(String [] args){
        SpringApplication.run(CouponCoreApplication.class, args);
    }
}
