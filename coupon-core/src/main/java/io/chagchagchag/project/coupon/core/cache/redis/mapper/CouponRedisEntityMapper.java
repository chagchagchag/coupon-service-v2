package io.chagchagchag.project.coupon.core.cache.redis.mapper;

import io.chagchagchag.project.coupon.core.cache.redis.valueobject.CouponIssueRedisEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponRedisEntityMapper {

    public CouponIssueRedisEntity fromCouponEntity(CouponEntity couponEntity){
        return new CouponIssueRedisEntity(
                couponEntity.getId(), couponEntity.getTitle(), couponEntity.getCouponAssignType(),
                couponEntity.getTotalQuantity(), couponEntity.getIssuedQuantity(),
                couponEntity.getDiscountAmount(), couponEntity.getMinAvailableAmount(),
                couponEntity.getIssueStartDateTime(), couponEntity.getIssueEndDateTime()
        );
    }

}
