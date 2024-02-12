package io.chagchagchag.project.coupon.api.redis.mapper;

import io.chagchagchag.project.coupon.api.dataaccess.valueobject.CouponEntityDto;
import io.chagchagchag.project.coupon.api.redis.valueobject.CouponIssueRedisEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponRedisEntityMapper {

    public CouponIssueRedisEntity fromCouponEntityDto(CouponEntityDto couponEntityDto){
        return new CouponIssueRedisEntity(
            couponEntityDto.couponId(), couponEntityDto.title(), couponEntityDto.couponAssignType(),
            couponEntityDto.totalQuantity(), couponEntityDto.issuedQuantity(),
            couponEntityDto.discountAmount(), couponEntityDto.minAvailableAmount(),
            couponEntityDto.issueStartDateTime(), couponEntityDto.issueEndDateTime()
        );
    }

    public CouponIssueRedisEntity fromCouponEntity(CouponEntity couponEntity){
        return new CouponIssueRedisEntity(
                couponEntity.getId(), couponEntity.getTitle(), couponEntity.getCouponAssignType(),
                couponEntity.getTotalQuantity(), couponEntity.getIssuedQuantity(),
                couponEntity.getDiscountAmount(), couponEntity.getMinAvailableAmount(),
                couponEntity.getIssueStartDateTime(), couponEntity.getIssueEndDateTime()
        );
    }

}
