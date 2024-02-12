package io.chagchagchag.project.coupon.api.application.rest;

import io.chagchagchag.project.coupon.api.application.valueobject.CouponIssueResponse;
import io.chagchagchag.project.coupon.api.application.valueobject.CouponIssueResultCode;
import io.chagchagchag.project.coupon.core.exception.CouponIssueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponApiRestControllerAdvice {

    @ExceptionHandler(CouponIssueException.class)
    public CouponIssueResponse couponIssueExceptionHandlerAdvice(CouponIssueException exception){
        return new CouponIssueResponse(CouponIssueResultCode.FAIL, exception.getErrorCode().message);
    }

}
