package io.chagchagchag.project.coupon.dataaccess.cache.redis.valueobject;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.chagchagchag.project.coupon.core.model.CouponAssignType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CouponRedisEntity(
    Long id,
    CouponAssignType couponAssignType,
    BigDecimal totalQuantity,
    Boolean isQuantityAvailable,
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime issueStartDate,

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime issueEndDate
) {


}
