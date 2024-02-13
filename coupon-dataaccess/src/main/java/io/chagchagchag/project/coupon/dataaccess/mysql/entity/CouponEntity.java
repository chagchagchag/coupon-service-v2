package io.chagchagchag.project.coupon.dataaccess.mysql.entity;

import io.chagchagchag.project.coupon.core.exception.CouponIssueException;
import io.chagchagchag.project.coupon.core.model.CouponAssignType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Column(nullable = false, name = "issue_start_datetime")
    private LocalDateTime issueStartDateTime;

    @Column(nullable = false, name = "issue_end_datetime")
    private LocalDateTime issueEndDateTime;

    @Builder(builderClassName = "DefaultBuilder", builderMethodName = "defaultBuilder")
    public CouponEntity(
        Long id, String title,
        CouponAssignType couponAssignType,
        BigDecimal totalQuantity, BigDecimal issuedQuantity,
        BigDecimal discountAmount, BigDecimal minAvailableAmount,
        LocalDateTime issueStartDateTime, LocalDateTime issueEndDateTime
    ){
        this.id = id;
        this.title = title;
        this.couponAssignType = couponAssignType;
        this.totalQuantity = totalQuantity;
        this.issuedQuantity = issuedQuantity;
        this.discountAmount = discountAmount;
        this.minAvailableAmount = minAvailableAmount;
        this.issueStartDateTime = issueStartDateTime;
        this.issueEndDateTime = issueEndDateTime;
    }

    @Builder(builderClassName = "FifoBuilderFromNow", builderMethodName = "fromNowFifoBuilder")
    public CouponEntity(
        Long id,    String title,
        BigDecimal totalQuantity, BigDecimal issuedQuantity,
        BigDecimal discountAmount, BigDecimal minAvailableAmount,
        LocalDateTime issueEndDateTime
    ){
        this.id = id;
        this.title = title;
        this.couponAssignType = CouponAssignType.FIFO;
        this.totalQuantity = totalQuantity;
        this.issuedQuantity = issuedQuantity;
        this.discountAmount = discountAmount;
        this.minAvailableAmount = minAvailableAmount;
        this.issueStartDateTime = LocalDateTime.now();
        this.issueEndDateTime = issueEndDateTime;
    }

    public Boolean hasIssueQuantityAvailable(){
        if (wasNeverIssued()) return true;
        return hasEnoughQuantity();
    }

    public Boolean wasNeverIssued(){
        return totalQuantity == null;
    }

    public Boolean hasEnoughQuantity(){
        if(totalQuantity == null) return true; // 쿠폰이 무한일 경우
        return totalQuantity.compareTo(issuedQuantity) > 0;
    }

    public Boolean isIssueDateAvailable(){
        LocalDateTime now = LocalDateTime.now();
        return issueStartDateTime.isBefore(now) && issueEndDateTime.isAfter(now);
    }

    public void validateCouponIssuable(){
        final LocalDateTime now = LocalDateTime.now();
        if(!hasEnoughQuantity())
            throw new CouponIssueException(EXCEEDED_COUPON_ISSUE_QUANTITY, EXCEEDED_COUPON_ISSUE_QUANTITY.message);

        if(issueEndDateTime.isBefore(now))
            throw new CouponIssueException(UNAVAILABLE_COUPON_ISSUE_DATE, UNAVAILABLE_COUPON_ISSUE_DATE.message);
    }

    public Boolean isCouponQuantityFull(){
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
