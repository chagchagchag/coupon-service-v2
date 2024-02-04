package io.chagchagchag.project.coupon.api.application.valueobject;

import lombok.Getter;

@Getter
public enum CouponIssueResultCode {
    SUCCESS("SUCCESS", "성공"),
    FAIL("FAIL", "실패");


    private final String messageEn;
    private final String messageKr;

    CouponIssueResultCode(
        String messageEn,
        String messageKr
    ){
        this.messageEn = messageEn;
        this.messageKr = messageKr;
    }
}
