package io.chagchagchag.project.coupon.api.application.valueobject;

public record CouponIssueRequest(
    Long userId,
    Long couponId
) {
}
