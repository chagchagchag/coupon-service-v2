package io.chagchagchag.project.coupon.dataaccess;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
        basePackages = {
                "io.chagchagchag.project.coupon.dataaccess"
        }
)
@EntityScan(
        basePackages = {
                "io.chagchagchag.project.coupon.dataaccess"
        }
)
@EnableJpaAuditing
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableCaching
@ComponentScan(basePackages = "io.chagchagchag.project.coupon")
@EnableAutoConfiguration
public class CouponDataAccessConfig {
}
