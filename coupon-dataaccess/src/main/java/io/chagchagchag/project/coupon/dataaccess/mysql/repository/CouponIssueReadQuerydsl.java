package io.chagchagchag.project.coupon.dataaccess.mysql.repository;

import com.querydsl.jpa.JPQLQueryFactory;
import io.chagchagchag.project.coupon.dataaccess.mysql.entity.CouponIssueEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static io.chagchagchag.project.coupon.core.dataaccess.entity.QCouponIssueEntity.couponIssueEntity;

@RequiredArgsConstructor
@Repository
public class CouponIssueReadQuerydsl {

    private final JPQLQueryFactory queryFactory;

    public CouponIssueEntity findFirstCouponIssue(Long couponId, Long userId){
        return queryFactory
            .selectFrom(couponIssueEntity)
            .where(couponIssueEntity.couponId.eq(couponId))
            .where(couponIssueEntity.userId.eq(userId))
            .fetchFirst();
    }

}
