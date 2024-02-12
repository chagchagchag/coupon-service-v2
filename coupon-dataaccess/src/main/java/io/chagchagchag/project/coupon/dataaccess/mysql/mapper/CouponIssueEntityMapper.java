package io.chagchagchag.project.coupon.dataaccess.mysql.mapper;

import io.chagchagchag.project.coupon.dataaccess.mysql.valueobject.CouponIssueEntityDto;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponIssueEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponIssueEntityMapper {
    public CouponIssueEntityDto toCouponIssueEntityDto(CouponIssueEntity issueEntity){
        return new CouponIssueEntityDto(
            issueEntity.getId(), issueEntity.getCouponId(), issueEntity.getUserId(),
            issueEntity.getIssuedDateTime(), issueEntity.getUsedDateTime()
        );
    }
}
