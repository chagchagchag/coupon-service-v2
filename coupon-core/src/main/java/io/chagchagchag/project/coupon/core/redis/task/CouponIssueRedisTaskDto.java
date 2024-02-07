package io.chagchagchag.project.coupon.core.redis.task;

public record CouponIssueRedisTaskDto(
    Long couponId, Long userId
){
}