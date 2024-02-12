package io.chagchagchag.project.coupon.dataaccess;

import io.chagchagchag.project.coupon.core.exception.ErrorCode;
import io.chagchagchag.project.coupon.dataaccess.mysql.mapper.CouponEntityMapper;
import io.chagchagchag.project.coupon.dataaccess.mysql.repository.CouponMysqlLockRepository;
import io.chagchagchag.project.coupon.dataaccess.mysql.valueobject.CouponEntityDto;
import io.chagchagchag.project.coupon.core.exception.CouponIssueException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CouponDataAccessService {

    private final CouponMysqlLockRepository couponMysqlLockRepository;
    private final CouponEntityMapper couponEntityMapper;

    // TODO :: 로컬 캐시 작업이 필요하다. MySQL 동시성 락으로 인한 부하를 줄이기 위함
    @Transactional(readOnly = true)
    public CouponEntityDto findCouponByCouponIdWithLock(Long couponId){
        return Optional
                .ofNullable(couponMysqlLockRepository.findCouponWithLock(couponId))
                .map(couponEntityMapper::toCouponEntityDto)
                .orElseThrow(()-> {throw new CouponIssueException(ErrorCode.COUPON_NOT_EXIST, ErrorCode.COUPON_ISSUE_FAIL.message);});
    }
}
