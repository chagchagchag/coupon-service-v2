package io.chagchagchag.project.coupon.api.redis;

import io.chagchagchag.project.coupon.api.dataaccess.CouponDataAccessService;
import io.chagchagchag.project.coupon.api.dataaccess.valueobject.CouponEntityDto;
import io.chagchagchag.project.coupon.api.redis.valueobject.CouponIssueQueueDto;
import io.chagchagchag.project.coupon.api.redis.repository.CouponRedisRepository;
import io.chagchagchag.project.coupon.api.redis.mapper.CouponRedisEntityMapper;
import io.chagchagchag.project.coupon.api.redis.valueobject.CouponIssueRedisEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponQueueService {

    private final CouponRedisRepository couponRedisRepository;
    private final CouponDataAccessService couponDataAccessService;
    private final CouponRedisEntityMapper couponRedisEntityMapper;

    /**
     * 1) MySQL 동시성 락 기반 CouponId 조회
     * 2) CouponIssueRedisEntity 객체 생성 + 유효성 체크
     * 3) CouponRedisRepository 에 CouponIssueRedisEntity 저장 (SADD, RPUSH)
     * @param queueDto : queue 계층에서 통용되는 queueDto
     */
    public void enqueue(CouponIssueQueueDto queueDto){
        // 1) MySQL 동시성 락 기반 CouponId 조회
        CouponEntityDto couponDto = couponDataAccessService.findCouponByCouponIdWithLock(queueDto.couponId());

        // 2) CouponIssueRedisEntity 객체 생성 + 유효성 체크
        CouponIssueRedisEntity couponIssueRedisEntity = couponRedisEntityMapper.fromCouponEntityDto(couponDto);
        couponIssueRedisEntity.validateCouponIssuable();

        // 3) CouponRedisRepository 에 CouponIssueRedisEntity 저장 (SADD, RPUSH)
        couponRedisRepository.enqueueCouponIssueRequest(couponIssueRedisEntity);
    }

}
