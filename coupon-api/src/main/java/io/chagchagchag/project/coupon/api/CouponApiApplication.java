package io.chagchagchag.project.coupon.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
    basePackages = {
        "io.chagchagchag.project.coupon.core.dataaccess",
        "io.chagchagchag.project.coupon.api.dataaccess"
    }
)
@EntityScan(
    basePackages = {
        "io.chagchagchag.project.coupon.core.dataaccess",
        "io.chagchagchag.project.coupon.api.dataaccess"
    }
)
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableCaching
@SpringBootApplication(scanBasePackages = "io.chagchagchag.project.coupon")
public class CouponApiApplication {
    public static void main(String [] args){
        System.setProperty("spring.config.name", "application-api");
        SpringApplication.run(CouponApiApplication.class, args);
    }
}
