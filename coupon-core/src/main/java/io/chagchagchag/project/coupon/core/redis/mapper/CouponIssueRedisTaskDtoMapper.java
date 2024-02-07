package io.chagchagchag.project.coupon.core.redis.mapper;

import io.chagchagchag.project.coupon.core.redis.task.CouponIssueRedisTaskDto;
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
