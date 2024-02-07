package io.chagchagchag.project.coupon.api.application;

import io.chagchagchag.project.coupon.api.application.mapper.CouponRequestMapper;
import io.chagchagchag.project.coupon.api.application.valueobject.CouponIssueRequest;
import io.chagchagchag.project.coupon.api.redis.CouponQueueService;
import io.chagchagchag.project.coupon.api.redis.valueobject.CouponIssueQueueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CouponApiApplicationService {

    private final CouponRequestMapper couponRequestMapper;
    private final CouponQueueService couponQueueService;

    public void issueAsync(CouponIssueRequest request) {
        CouponIssueQueueDto queueDto = couponRequestMapper.toCouponQueueIssueDto(request);
        couponQueueService.enqueue(queueDto);
    }
}
