package io.chagchagchag.project.coupon.core.domain;

import io.chagchagchag.project.coupon.core.domain.event.CouponIssueCreatedEvent;
import io.chagchagchag.project.coupon.core.domain.event.factory.CouponIssueCreatedEventFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponDomainService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CouponIssueCreatedEventFactory couponIssueCreatedEventFactory;
    public void publishEvent(Long couponId){
        final CouponIssueCreatedEvent couponIssueCreatedEvent = couponIssueCreatedEventFactory.defaultEvent(couponId);
        applicationEventPublisher.publishEvent(couponIssueCreatedEvent);
    }
}
