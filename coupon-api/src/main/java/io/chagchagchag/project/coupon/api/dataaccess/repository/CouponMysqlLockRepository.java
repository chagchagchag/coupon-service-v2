package io.chagchagchag.project.coupon.api.dataaccess.repository;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CouponMysqlLockRepository extends JpaRepository<CouponEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CouponEntity C WHERE c.id = :id")
    Optional<CouponEntity> findCouponWithLock(Long id);
}
