package io.chagchagchag.project.coupon.api.fixtures;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.factory.CouponEntityFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CouponEntityFixtures {
    private static final CouponEntityFactory couponEntityFactory = new CouponEntityFactory();
    public static CouponEntity ofNoError(){
        return couponEntityFactory.newDefaultCoupon("");
    }

    public static CouponEntity ofNoError(Long couponId){
        return couponEntityFactory.newDefaultCoupon("", couponId);
    }

    public static CouponEntity ofInvalidQuantity1(){
        return couponEntityFactory.newDefaultCoupon(
            "",
                BigDecimal.ONE, BigDecimal.TWO
        );
    }

    public static CouponEntity ofInvalidIssueDate1(){
        return couponEntityFactory.newDefaultCouponWithExpiration(
            "",
                BigDecimal.TWO,BigDecimal.ONE,
                LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(2)
        );
    }

}
