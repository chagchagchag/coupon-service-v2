package io.chagchagchag.project.coupon.issuer.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.chagchagchag.project.coupon.core.cache.redis.valueobject.CouponIssueQueueDto;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import io.chagchagchag.project.coupon.dataaccess.CouponIssueDataAccessService;
import io.chagchagchag.project.coupon.dataaccess.cache.CouponIssueRedisRepository;
import io.chagchagchag.project.coupon.issuer.domain.CouponDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@EnableScheduling
@Component
public class CouponIssuerScheduler {
    private final CouponIssueRedisRepository couponIssueRedisRepository;
    private final CouponIssueDataAccessService couponIssueDataAccessService;
    private final CouponDomainService couponDomainService;

    @Scheduled(fixedDelay = 500)
    @Transactional
    public void issue() throws JsonProcessingException {
        while(couponIssueRedisRepository.existsWaitingForBeingIssued()){
            CouponIssueQueueDto queueDto = couponIssueRedisRepository.pollOne();
            log.info("[Publish] start >> " + queueDto);

            CouponEntity coupon = couponIssueDataAccessService.issue(queueDto.couponId(), queueDto.userId());
            coupon.validateCouponIssuable();
            couponDomainService.publishEvent(queueDto.couponId());
        }
    }
}
