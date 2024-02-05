package io.chagchagchag.project.coupon.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy = true)
@EnableCaching
@SpringBootApplication
public class CouponApiApplication {
    public static void main(String [] args){
        SpringApplication.run(CouponApiApplication.class, args);
    }
}
