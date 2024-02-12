package io.chagchagchag.project.coupon.dataaccess.mysql.repository;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponMysqlLockRepository extends JpaRepository<CouponEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CouponEntity c WHERE c.id = :id")
    CouponEntity findCouponWithLock(@Param("id") Long id);
}
