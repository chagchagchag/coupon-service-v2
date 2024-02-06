package io.chagchagchag.project.coupon.api.application;

import io.chagchagchag.project.coupon.api.application.mapper.CouponRequestMapper;
import io.chagchagchag.project.coupon.api.application.valueobject.CouponIssueRequest;
import io.chagchagchag.project.coupon.api.queue.CouponQueueService;
import io.chagchagchag.project.coupon.api.queue.valueobject.CouponIssueQueueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CouponApplicationService {

    private final CouponRequestMapper couponRequestMapper;
    private final CouponQueueService couponQueueService;

    public void issueAsync(CouponIssueRequest request) {
        CouponIssueQueueDto queueDto = couponRequestMapper.toCouponQueueIssueDto(request);
        couponQueueService.enqueue(queueDto);
    }
}
