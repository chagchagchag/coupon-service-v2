package io.chagchagchag.project.coupon.api.application.mapper;

import io.chagchagchag.project.coupon.api.application.valueobject.CouponIssueRequest;
import io.chagchagchag.project.coupon.api.queue.valueobject.CouponQueueIssueDto;
import org.springframework.stereotype.Component;

@Component
public class CouponRequestMapper {
    public CouponQueueIssueDto toCouponQueueIssueDto(CouponIssueRequest request){
        return new CouponQueueIssueDto(request.userId(), request.couponId());
    }
}
