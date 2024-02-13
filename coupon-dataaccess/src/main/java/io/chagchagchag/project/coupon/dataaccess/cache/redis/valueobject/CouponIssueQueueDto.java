package io.chagchagchag.project.coupon.dataaccess.cache.redis.valueobject;

public record CouponIssueQueueDto(
    Long userId,
    Long couponId
){
}
