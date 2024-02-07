package io.chagchagchag.project.coupon.api.dataaccess;

import io.chagchagchag.project.coupon.api.dataaccess.mapper.CouponEntityDtoMapper;
import io.chagchagchag.project.coupon.api.dataaccess.repository.CouponMysqlLockRepository;
import io.chagchagchag.project.coupon.api.dataaccess.valueobject.CouponEntityDto;
import io.chagchagchag.project.coupon.core.exception.CouponIssueException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.chagchagchag.project.coupon.core.exception.ErrorCode.COUPON_NOT_EXIST;

@RequiredArgsConstructor
@Service
public class CouponDataAccessService {

    private final CouponMysqlLockRepository couponMysqlLockRepository;
    private final CouponEntityDtoMapper couponEntityDtoMapper;

    // TODO :: 로컬 캐시 작업이 필요하다. MySQL 동시성 락으로 인한 부하를 줄이기 위함

    @Transactional(readOnly = true)
    public CouponEntityDto findCouponByCouponIdWithLock(Long couponId){
        return couponMysqlLockRepository
                .findCouponWithLock(couponId)
                .map(couponEntityDtoMapper::fromCouponEntity)
                .orElseThrow(() -> {
                    throw new CouponIssueException(COUPON_NOT_EXIST, COUPON_NOT_EXIST.message);
                });
    }

}
