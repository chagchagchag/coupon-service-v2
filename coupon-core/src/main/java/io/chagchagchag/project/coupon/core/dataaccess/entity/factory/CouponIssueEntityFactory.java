package io.chagchagchag.project.coupon.core.dataaccess.entity.factory;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponIssueEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CouponIssueEntityFactory {
    public CouponIssueEntity newDefaultCouponIssue(
        Long couponId, Long userId
    ){
        return CouponIssueEntity.defaultBuilder()
                .couponId(couponId)
                .userId(userId)
                .issuedDateTime(LocalDateTime.now())
                .build();
    }
}
