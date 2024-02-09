package io.chagchagchag.project.coupon.core.dataaccess.entity.factory;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponIssueEntity;
import org.springframework.stereotype.Component;

@Component
public class CouponIssueEntityFactory {
    public CouponIssueEntity newDefaultCouponIssue(
        Long couponId, Long issueId
    ){
        return CouponIssueEntity.defaultBuilder()
                .couponId(couponId)
                .id(issueId)
                .build();
    }
}
