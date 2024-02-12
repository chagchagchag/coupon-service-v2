package io.chagchagchag.project.coupon.core.cache.redis.valueobject;

public record CouponIssueQueueDto(
    Long userId,
    Long couponId
){
}
