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
