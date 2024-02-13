package io.chagchagchag.project.coupon.dataaccess;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import io.chagchagchag.project.coupon.core.exception.CouponIssueException;
import io.chagchagchag.project.coupon.core.exception.ErrorCode;
import io.chagchagchag.project.coupon.dataaccess.mysql.repository.CouponJpaRepository;
import io.chagchagchag.project.coupon.dataaccess.mysql.repository.CouponMysqlLockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static io.chagchagchag.project.coupon.core.exception.ErrorCode.COUPON_NOT_EXIST;

@RequiredArgsConstructor
@Service
public class CouponDataAccessService {
    private final CouponJpaRepository couponJpaRepository;
    private final CouponMysqlLockRepository couponMysqlLockRepository;

    @Transactional(readOnly = true)
    public CouponEntity findCouponByCouponIdWithLock(Long couponId){
        return Optional
                .ofNullable(couponMysqlLockRepository.findCouponWithLock(couponId))
                .orElseThrow(()-> {throw new CouponIssueException(COUPON_NOT_EXIST, ErrorCode.COUPON_ISSUE_FAIL.message);});
    }

    @Transactional(readOnly = true)
    public CouponEntity findCouponById(Long couponId){
        return couponJpaRepository
                .findById(couponId)
                .orElseThrow(() -> {
                    throw new CouponIssueException(COUPON_NOT_EXIST, COUPON_NOT_EXIST.message);
                });
    }
}
