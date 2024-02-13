package io.chagchagchag.project.coupon.dataaccess.mysql.entity.factory.defaultvalue;

import lombok.Getter;

import java.time.Period;
import java.time.temporal.TemporalAmount;

@Getter
public enum DefaultDuration {
    THOUSAND_DAY(Period.ofDays(1000));

    final TemporalAmount temporalAmount;

    DefaultDuration(
            TemporalAmount temporalAmount
    ){
        this.temporalAmount = temporalAmount;
    }
}
