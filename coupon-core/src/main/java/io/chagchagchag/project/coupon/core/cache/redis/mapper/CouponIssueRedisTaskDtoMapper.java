package io.chagchagchag.project.coupon.core.cache.redis.mapper;

import io.chagchagchag.project.coupon.core.cache.redis.task.CouponIssueRedisTaskDto;
import org.springframework.stereotype.Component;

@Component
public class CouponIssueRedisTaskDtoMapper {

    public CouponIssueRedisTaskDto fromCouponIdAndUserId(
        Long couponId, Long userId
    ){
        return new CouponIssueRedisTaskDto(
            couponId, userId
        );
    }

}
