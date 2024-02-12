package io.chagchagchag.project.coupon.core.cache.redis.valueobject;

import io.chagchagchag.project.coupon.core.exception.CouponIssueException;
import io.chagchagchag.project.coupon.core.exception.ErrorCode;
import io.chagchagchag.project.coupon.core.model.CouponAssignType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.chagchagchag.project.coupon.core.exception.ErrorCode.EXCEEDED_COUPON_ISSUE_QUANTITY;
import static io.chagchagchag.project.coupon.core.exception.ErrorCode.UNAVAILABLE_COUPON_ISSUE_DATE;

public record CouponIssueRedisEntity(
    Long couponId,
    String title,
    CouponAssignType couponAssignType,
    BigDecimal totalQuantity,
    BigDecimal issuedQuantity,
    BigDecimal discountAmount,
    BigDecimal minAvailableAmount,
    LocalDateTime issueStartDateTime,
    LocalDateTime issueEndDateTime
) {

    public Boolean isExpiredCoupon(){
        return issueEndDateTime.isBefore(LocalDateTime.now()) || !hasEnoughQuantity();
    }

    public Boolean hasEnoughQuantity(){
        return totalQuantity.compareTo(issuedQuantity) > 0;
    }

    public void validateCouponIssuable(){
        if(!hasEnoughQuantity())
            throw new CouponIssueException(EXCEEDED_COUPON_ISSUE_QUANTITY, EXCEEDED_COUPON_ISSUE_QUANTITY.message);

        if(isExpiredCoupon())
            throw new CouponIssueException(UNAVAILABLE_COUPON_ISSUE_DATE, UNAVAILABLE_COUPON_ISSUE_DATE.message);
    }
}
