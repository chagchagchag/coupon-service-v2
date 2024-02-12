package io.chagchagchag.project.coupon.api.domain;

import io.chagchagchag.project.coupon.api.cache.CouponApiCacheService;
import io.chagchagchag.project.coupon.core.domain.event.CouponIssueCreatedEvent;
import io.chagchagchag.project.coupon.core.domain.event.factory.CouponIssueCreatedEventFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Service
public class CouponDomainService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CouponIssueCreatedEventFactory couponIssueCreatedEventFactory;
    // TODO :: CouponApiRedisService, CouponApiCaffeineService 로 분리할지 고민 ...
    private final CouponApiCacheService couponApiCacheService;

    public void publishEvent(Long couponId){
        final CouponIssueCreatedEvent couponIssueCreatedEvent = couponIssueCreatedEventFactory.defaultEvent(couponId);
        applicationEventPublisher.publishEvent(couponIssueCreatedEvent);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCouponIssueCreatedEvent(CouponIssueCreatedEvent event){
        log.info("issue complete. Domain Event handler start >>> ");
        couponApiCacheService.putRedisCouponCache(event.couponId());
        couponApiCacheService.putLocalCouponCache(event.couponId());
        log.info("issue complete. Domain Event handler end >>> ");
    }
}
