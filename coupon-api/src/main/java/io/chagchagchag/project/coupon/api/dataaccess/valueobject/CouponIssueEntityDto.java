package io.chagchagchag.project.coupon.api.dataaccess.valueobject;

import io.chagchagchag.project.coupon.core.model.CouponAssignType;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public record CouponIssueEntityDto (
    Long id,
    Long couponId,
    Long userId,
    LocalDateTime issuedDateTime,
    LocalDateTime usedDateTime
){
}
