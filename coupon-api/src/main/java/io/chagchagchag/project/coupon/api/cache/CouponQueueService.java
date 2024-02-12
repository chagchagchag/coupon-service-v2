package io.chagchagchag.project.coupon.api.cache;

import io.chagchagchag.project.coupon.api.cache.redis.repository.CouponIssueRedisRepository;
import io.chagchagchag.project.coupon.api.dataaccess.CouponIssueDataAccessService;
import io.chagchagchag.project.coupon.core.cache.redis.mapper.CouponRedisEntityMapper;
import io.chagchagchag.project.coupon.core.cache.redis.valueobject.CouponIssueQueueDto;
import io.chagchagchag.project.coupon.core.cache.redis.valueobject.CouponIssueRedisEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponQueueService {

    private final CouponIssueRedisRepository couponIssueRedisRepository;
    private final CouponIssueDataAccessService couponIssueDataAccessService;
    private final CouponRedisEntityMapper couponRedisEntityMapper;

    /**
     * 1) MySQL 동시성 락 기반 CouponId 조회
     * 2) CouponIssueRedisEntity 객체 생성 + 유효성 체크
     * 3) CouponRedisRepository 에 CouponIssueRedisEntity 저장 (SADD, RPUSH)
     * @param queueDto : queue 계층에서 통용되는 queueDto
     */
    public void enqueue(CouponIssueQueueDto queueDto){
        // 1) MySQL 동시성 락 기반 CouponId 조회
        CouponEntity coupon = couponIssueDataAccessService.findCouponByCouponIdWithLock(queueDto.couponId());

        // 2) CouponIssueRedisEntity 객체 생성 + 유효성 체크
        CouponIssueRedisEntity couponIssueRedisEntity = couponRedisEntityMapper.fromCouponEntity(coupon);
        couponIssueRedisEntity.validateCouponIssuable();

        // 3) CouponRedisRepository 에 CouponIssueRedisEntity 저장 (SADD, RPUSH)
        couponIssueRedisRepository.enqueueCouponIssueRequest(queueDto.couponId(), queueDto.userId(), couponIssueRedisEntity.totalQuantity());
    }

}