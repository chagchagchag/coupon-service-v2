package io.chagchagchag.project.coupon.core.dataaccess.repository;

import com.querydsl.jpa.JPQLQueryFactory;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponIssueEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CouponIssueReadQuerydsl {

    private final JPQLQueryFactory queryFactory;

    public CouponIssueEntity findFirstCouponIssue(long couponId, long userId){

        return null;
    }

}
