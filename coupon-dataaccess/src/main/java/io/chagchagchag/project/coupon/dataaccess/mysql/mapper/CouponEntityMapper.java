package io.chagchagchag.project.coupon.dataaccess.mysql.mapper;

import io.chagchagchag.project.coupon.dataaccess.mysql.valueobject.CouponEntityDto;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponEntityMapper {
    public CouponEntityDto toCouponEntityDto(CouponEntity couponEntity){
        return new CouponEntityDto(
            couponEntity.getId(),
            couponEntity.getTitle(),
            couponEntity.getCouponAssignType(),
            couponEntity.getTotalQuantity(),
            couponEntity.getIssuedQuantity(),
            couponEntity.getDiscountAmount(),
            couponEntity.getMinAvailableAmount(),
            couponEntity.getIssueStartDateTime(),
            couponEntity.getIssueEndDateTime()
        );
    }
}
