package io.chagchagchag.project.coupon.api.fixtures;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponIssueEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.factory.CouponIssueEntityFactory;

public class CouponIssueEntityFixtures {
    private static final CouponIssueEntityFactory couponIssueFactory = new CouponIssueEntityFactory();
    public static CouponIssueEntity ofNoError(){
        return couponIssueFactory.newDefaultCouponIssue(1L, 1L);
    }

    public static CouponIssueEntity ofNoError(Long couponId, Long issueId){
        return couponIssueFactory.newDefaultCouponIssue(couponId, issueId);
    }
}
