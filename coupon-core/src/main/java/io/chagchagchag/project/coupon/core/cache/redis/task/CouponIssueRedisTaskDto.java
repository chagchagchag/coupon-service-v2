package io.chagchagchag.project.coupon.core.cache.redis.task;

public record CouponIssueRedisTaskDto(
    Long couponId, Long userId
){
}