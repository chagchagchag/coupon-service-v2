package io.chagchagchag.project.coupon.api.dataaccess.repository;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJpaRepository extends JpaRepository<CouponEntity, Long> {
}
