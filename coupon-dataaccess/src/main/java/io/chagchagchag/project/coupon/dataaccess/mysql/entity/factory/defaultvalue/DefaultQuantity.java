package io.chagchagchag.project.coupon.dataaccess.mysql.entity.factory.defaultvalue;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum DefaultQuantity {
    ISSUED_QUANTITY(BigDecimal.valueOf(0)),
    TOTAL_QUANTITY(BigDecimal.valueOf(100_0000));

    DefaultQuantity(BigDecimal quantity){
        this.quantity = quantity;
    }

    private final BigDecimal quantity;
}
