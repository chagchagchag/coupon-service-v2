package io.chagchagchag.project.coupon.core.dataaccess.entity.factory.defaultvalue;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum DefaultAmount {
    // 할인 금액
    DISCOUNT_AMOUNT(BigDecimal.valueOf(100000)),
    // 최소 할인 금액
    MIN_AVAILABLE_AMOUNT(BigDecimal.ONE);

    private final BigDecimal amount;

    DefaultAmount(BigDecimal amount){
        this.amount = amount;
    }
}
