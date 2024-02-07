package io.chagchagchag.project.coupon.api.redis.valueobject;

public record CouponIssueQueueDto(
    Long userId,
    Long couponId
){
}
