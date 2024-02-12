package io.chagchagchag.project.coupon.api.cache;

import io.chagchagchag.project.coupon.api.dataaccess.CouponIssueDataAccessService;
import io.chagchagchag.project.coupon.core.cache.redis.valueobject.CouponRedisEntity;
import io.chagchagchag.project.coupon.core.cache.redis.valueobject.factory.CouponRedisEntityFactory;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CouponApiCacheService {

    private final CouponIssueDataAccessService couponIssueDataAccessService;
    private final CouponRedisEntityFactory couponRedisEntityFactory;

    @CachePut(cacheNames = "coupon")
    public CouponRedisEntity putRedisCouponCache(Long couponId){
        CouponEntity coupon = couponIssueDataAccessService.findCouponByCouponIdWithLock(couponId);
        return couponRedisEntityFactory.fromCouponEntity(coupon);
    }

    @Cacheable(cacheNames = "coupon")
    public CouponRedisEntity getRedisCouponCache(Long couponId){
        CouponEntity coupon = couponIssueDataAccessService.findCouponByCouponIdWithLock(couponId);
        return couponRedisEntityFactory.fromCouponEntity(coupon);
    }

    @CachePut(cacheNames = "coupon", cacheManager = "caffeineCacheManager")
    public CouponRedisEntity putLocalCouponCache(Long couponId){
        return getLocalCouponCache(couponId);
    }

    @Cacheable(cacheNames = "coupon", cacheManager = "caffeineCacheManager")
    public CouponRedisEntity getLocalCouponCache(Long couponId){
        return proxy().getRedisCouponCache(couponId);
    }

    private CouponApiCacheService proxy(){
        return ((CouponApiCacheService) AopContext.currentProxy());
    }
}