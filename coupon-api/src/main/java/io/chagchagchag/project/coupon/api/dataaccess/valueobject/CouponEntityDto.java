package io.chagchagchag.project.coupon.api.dataaccess.valueobject;

import io.chagchagchag.project.coupon.core.model.CouponAssignType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CouponEntityDto (
    Long couponId,
    String title,
    CouponAssignType couponAssignType,
    BigDecimal totalQuantity,
    BigDecimal issuedQuantity,
    BigDecimal discountAmount,
    BigDecimal minAvailableAmount,
    LocalDateTime issueStartDateTime,
    LocalDateTime issueEndDateTime
){
}
