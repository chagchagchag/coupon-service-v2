package io.chagchagchag.project.coupon.dataaccess.cache;

import io.chagchagchag.project.coupon.core.cache.redis.mapper.CouponRedisEntityMapper;
import io.chagchagchag.project.coupon.core.cache.redis.valueobject.CouponIssueQueueDto;
import io.chagchagchag.project.coupon.core.cache.redis.valueobject.CouponIssueRedisEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import io.chagchagchag.project.coupon.dataaccess.CouponDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponQueueService {

    private final CouponIssueRedisRepository couponIssueRedisRepository;
    private final CouponDataAccessService couponDataAccessService;
    private final CouponRedisEntityMapper couponRedisEntityMapper;

    /**
     * 1) Coupon 조회
     * 2) CouponIssueRedisEntity 객체 생성 + 유효성 체크
     * 3) CouponRedisRepository 에 CouponIssueRedisEntity 저장 (SADD, RPUSH)
     * @param queueDto : queue 계층에서 통용되는 queueDto
     */
    public void enqueue(CouponIssueQueueDto queueDto){
        // 1) Coupon 조회
        CouponEntity coupon = couponDataAccessService.findCouponById(queueDto.couponId());

        // 2) CouponIssueRedisEntity 객체 생성 + 유효성 체크
        CouponIssueRedisEntity couponIssueRedisEntity = couponRedisEntityMapper.fromCouponEntity(coupon);
        couponIssueRedisEntity.validateCouponIssuable();

        // 3) CouponRedisRepository 에 CouponIssueRedisEntity 저장 (SADD, RPUSH)
        couponIssueRedisRepository.enqueueCouponIssueRequest(queueDto, couponIssueRedisEntity.totalQuantity());
    }

}
