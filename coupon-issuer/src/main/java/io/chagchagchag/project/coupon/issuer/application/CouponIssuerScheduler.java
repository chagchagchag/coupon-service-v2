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
        // TODO |
        //  Batch 작업이 아닌 개별 단건 처리 작업 기반으로 구현해두었다.
        //  추후 Batch(스프링배치 말고 여러건 묶음처리) 작업으로 전환할지 검토 예정
        while(couponIssueRedisRepository.existsWaitingForBeingIssued()){
            // 작업 큐 인출 (poll())
            CouponIssueQueueDto queueDto = couponIssueRedisRepository.pollOne();

            // 쿠폰 발급 (issue)
            CouponEntity coupon = couponIssueDataAccessService.issue(queueDto.couponId(), queueDto.userId());

            // coupon 검증 (validate)
            coupon.validateCouponIssuable();

            // DomainEvent Publish
            log.info("[Publish] start >> " + queueDto);
            couponDomainService.publishEvent(queueDto.couponId());
            couponIssueRedisRepository.removeOldestOne();
        }
    }
}
