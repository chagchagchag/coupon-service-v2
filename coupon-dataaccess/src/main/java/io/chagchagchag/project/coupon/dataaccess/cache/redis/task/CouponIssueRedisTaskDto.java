package io.chagchagchag.project.coupon.dataaccess.cache.redis.task;

public record CouponIssueRedisTaskDto(
    Long couponId, Long userId
){
}