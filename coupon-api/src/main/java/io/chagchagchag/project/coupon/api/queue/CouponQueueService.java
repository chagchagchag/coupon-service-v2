package io.chagchagchag.project.coupon.api.queue;

import io.chagchagchag.project.coupon.api.queue.valueobject.CouponQueueIssueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponQueueService {

    public void enqueue(CouponQueueIssueDto queueDto){
        // redis call 등등
    }

}
