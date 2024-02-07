package io.chagchagchag.project.coupon.api.application.rest;

import io.chagchagchag.project.coupon.api.application.CouponApiApplicationService;
import io.chagchagchag.project.coupon.api.application.valueobject.CouponIssueRequest;
import io.chagchagchag.project.coupon.api.application.valueobject.CouponIssueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static io.chagchagchag.project.coupon.api.application.valueobject.CouponIssueResultCode.SUCCESS;

@RequiredArgsConstructor
@RestController
public class CouponApiController {

    private final CouponApiApplicationService couponApiApplicationService;

    @PostMapping("/coupon/issue")
    public CouponIssueResponse issue(@RequestBody CouponIssueRequest request){
        couponApiApplicationService.issueAsync(request);
        return new CouponIssueResponse(SUCCESS, SUCCESS.getMessageKr());
    }

}
