package io.chagchagchag.project.coupon.api.dataaccess.valueobject;

import java.time.LocalDateTime;

public record CouponIssueEntityDto (
    Long id,
    Long couponId,
    Long userId,
    LocalDateTime issuedDateTime,
    LocalDateTime usedDateTime
){
}
