package io.chagchagchag.project.coupon.core.domain.event.factory;

import io.chagchagchag.project.coupon.core.domain.event.CouponIssueCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class CouponIssueCreatedEventFactory {
    public CouponIssueCreatedEvent defaultEvent(Long id){
        return new CouponIssueCreatedEvent(id);
    }
}
