package io.chagchagchag.project.coupon.api.application.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(value = NON_NULL)
public record CouponIssueResponse(
        CouponIssueResultCode resultCode,
        String message
) {
}
