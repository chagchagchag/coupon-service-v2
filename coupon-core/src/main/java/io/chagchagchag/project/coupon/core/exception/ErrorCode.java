package io.chagchagchag.project.coupon.core.exception;

public enum ErrorCode {
    EXCEEDED_COUPON_ISSUE_QUANTITY("쿠폰 발급 가능 수량 초과"),
    UNAVAILABLE_COUPON_ISSUE_DATE("쿠폰 발급 기한 오류 - 발급 기한을 확인해주세요."),
    COUPON_NOT_EXIST("쿠폰이 존재하지 않습니다."),
    COUPON_ISSUE_FAIL("쿠폰 발급 실패. 잠시 후 다시 시도해주세요."),
    DUPLICATED_COUPON_ISSUE_REQUEST("이미 발급된 쿠폰입니다."); // 특정 사용자에게 이미 발급되었을 때

    public final String message;

    ErrorCode(String message){
        this.message = message;
    }
}
