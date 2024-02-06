package io.chagchagchag.project.coupon.api.dataaccess;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponIssueJpaRepository extends JpaRepository<CouponEntity, Long> {
}
