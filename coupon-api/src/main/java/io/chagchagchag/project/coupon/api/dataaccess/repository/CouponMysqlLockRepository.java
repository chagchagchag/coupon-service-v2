package io.chagchagchag.project.coupon.api.dataaccess.repository;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponMysqlLockRepository extends JpaRepository<CouponEntity, Long> {
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @Query("SELECT c FROM CouponEntity C WHERE c.id = :id")
//    Optional<CouponEntity> findCouponWithLock(Long id);
}
