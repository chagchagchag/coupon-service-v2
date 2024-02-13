package io.chagchagchag.project.coupon.dataaccess.mysql.repository;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponIssueJpaRepository extends JpaRepository<CouponIssueEntity, Long> {
    @Query("SELECT ci from CouponIssueEntity ci where ci.couponId = :couponId and ci.userId = :userId")
    public CouponIssueEntity findCouponIssue(@Param("couponId") Long couponId, @Param("userId") Long userId);
}
