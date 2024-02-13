package io.chagchagchag.project.coupon.dataaccess.cache.redis.resultcode;

import io.chagchagchag.project.coupon.core.exception.CouponIssueException;
import lombok.Getter;

import static io.chagchagchag.project.coupon.core.exception.ErrorCode.DUPLICATED_COUPON_ISSUE_REQUEST;
import static io.chagchagchag.project.coupon.core.exception.ErrorCode.EXCEEDED_COUPON_ISSUE_QUANTITY;

@Getter
public enum CouponIssueEnqueueResultCode {
    SUCCESS("SUCCESS", 1),
    DUPLICATED_ENQUEUE_REQUEST("DUPLICATED_ENQUEUE_REQUEST", 2),
    EXCEEDED_TOTAL_COUPON_ISSUE_LIMIT("EXCEEDED_TOTAL_COUPON_ISSUE_LIMIT", 3);

    private final String message;
    private final int code;
    CouponIssueEnqueueResultCode(String message, int code){
        this.message = message;
        this.code = code;
    }

    public static void validateResultCode(CouponIssueEnqueueResultCode resultCode){
        if(resultCode.equals(CouponIssueEnqueueResultCode.EXCEEDED_TOTAL_COUPON_ISSUE_LIMIT))
            throw new CouponIssueException(EXCEEDED_COUPON_ISSUE_QUANTITY, EXCEEDED_COUPON_ISSUE_QUANTITY.message);
        else if(resultCode.equals(CouponIssueEnqueueResultCode.DUPLICATED_ENQUEUE_REQUEST))
            throw new CouponIssueException(DUPLICATED_COUPON_ISSUE_REQUEST, DUPLICATED_COUPON_ISSUE_REQUEST.message);
    }
}
