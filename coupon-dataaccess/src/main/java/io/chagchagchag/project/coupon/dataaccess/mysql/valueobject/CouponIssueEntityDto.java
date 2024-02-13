package io.chagchagchag.project.coupon.dataaccess.mysql.valueobject;

import java.time.LocalDateTime;

public record CouponIssueEntityDto (
    Long id,
    Long couponId,
    Long userId,
    LocalDateTime issuedDateTime,
    LocalDateTime usedDateTime
){
}
