package io.chagchagchag.project.coupon.core.dataaccess.entity;

import io.chagchagchag.project.coupon.core.exception.CouponIssueException;
import io.chagchagchag.project.coupon.core.model.CouponAssignType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.chagchagchag.project.coupon.core.exception.ErrorCode.EXCEEDED_COUPON_ISSUE_QUANTITY;
import static io.chagchagchag.project.coupon.core.exception.ErrorCode.UNAVAILABLE_COUPON_ISSUE_DATE;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "coupons")
public class CouponEntity extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CouponAssignType couponAssignType;

    private BigDecimal totalQuantity;

    @Column(nullable = false)
    private BigDecimal issuedQuantity;

    @Column(nullable = false)
    private BigDecimal discountAmount;

    @Column(nullable = false)
    private BigDecimal minAvailableAmount;

    @Column(nullable = false)
    private LocalDateTime issueStartDateTime;

    @Column(nullable = false)
    private LocalDateTime issueEndDateTime;

    public Boolean hasIssueQuantityAvailable(){
        if (wasNeverIssued()) return true;
        return hasEnoughQuantity();
    }

    public Boolean wasNeverIssued(){
        return totalQuantity == null;
    }

    public Boolean hasEnoughQuantity(){
        return totalQuantity.compareTo(issuedQuantity) > 0;
    }

    public Boolean isIssueDateAvailable(){
        LocalDateTime now = LocalDateTime.now();
        return issueStartDateTime.isBefore(now) && issueEndDateTime.isAfter(now);
    }

    public Boolean isExpiredCoupon(){
        LocalDateTime now = LocalDateTime.now();
        return issueEndDateTime.isBefore(now) || !hasEnoughQuantity();
    }

    public void issue(){
        if(!hasIssueQuantityAvailable())
            throw new CouponIssueException(
                EXCEEDED_COUPON_ISSUE_QUANTITY,
                "발급 가능 한도 초과 (total = %s, issued = %s)".formatted(totalQuantity, issuedQuantity)
            );

        if(!isIssueDateAvailable()){
            throw new CouponIssueException(
                UNAVAILABLE_COUPON_ISSUE_DATE,
                "발급 가능 기간이 아닙니다. 요청일자 : %s, 발급 가능 기간 : %s ~ %s".formatted(LocalDateTime.now(), issueStartDateTime, issueEndDateTime)
            );
        }

        issuedQuantity = issuedQuantity.add(BigDecimal.ONE);
    }
}
