package io.chagchagchag.project.coupon.core.dataaccess.entity.factory;


import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.factory.defaultvalue.DefaultAmount;
import io.chagchagchag.project.coupon.core.dataaccess.entity.factory.defaultvalue.DefaultDuration;
import io.chagchagchag.project.coupon.core.dataaccess.entity.factory.defaultvalue.DefaultQuantity;
import io.chagchagchag.project.coupon.core.model.CouponAssignType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class CouponEntityFactory {

    /**
     * title 외의 기본필드 설정이 갖춰진 Fifo 쿠폰을 생성 (할인액, 최소할인금액 등은 디폴트 값을 따름)
     * @param title 쿠폰명
     * @return CouponEntity
     */
    public CouponEntity newDefaultCoupon(
        String title
    ){
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime end = now.plus(DefaultDuration.THOUSAND_DAY.getTemporalAmount());

        return CouponEntity.defaultBuilder()
                .title(title)
                .couponAssignType(CouponAssignType.FIFO)
                .totalQuantity(DefaultQuantity.TOTAL_QUANTITY.getQuantity())
                .issuedQuantity(DefaultQuantity.ISSUED_QUANTITY.getQuantity())
                .discountAmount(DefaultAmount.DISCOUNT_AMOUNT.getAmount())
                .minAvailableAmount(DefaultAmount.MIN_AVAILABLE_AMOUNT.getAmount())
                .issueStartDateTime(now)
                .issueEndDateTime(end)
                .build();
    }

    /**
     * title, totalQuantity 외의 기본필드 설정이 갖춰진 Fifo 쿠폰을 생성 (할인액, 최소할인금액 등은 디폴트 값을 따름)
     * @param title 쿠폰 명
     * @param totalQuantity 쿠폰 전체 수량
     * @return CouponEntity
     */
    public CouponEntity newDefaultCoupon(
        String title,
        BigDecimal totalQuantity
    ){
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime end = now.plus(DefaultDuration.THOUSAND_DAY.getTemporalAmount());

        return CouponEntity.defaultBuilder()
                .title(title)
                .couponAssignType(CouponAssignType.FIFO)
                .totalQuantity(totalQuantity)
                .issuedQuantity(BigDecimal.ZERO)
                .discountAmount(DefaultAmount.DISCOUNT_AMOUNT.getAmount())
                .minAvailableAmount(DefaultAmount.MIN_AVAILABLE_AMOUNT.getAmount())
                .issueStartDateTime(now)
                .issueEndDateTime(end)
                .build();
    }

    public CouponEntity newDefaultCoupon(
        String title,
        BigDecimal totalQuantity, BigDecimal issuedQuantity
    ){
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime end = now.plus(DefaultDuration.THOUSAND_DAY.getTemporalAmount());

        return CouponEntity.defaultBuilder()
                .title(title)
                .couponAssignType(CouponAssignType.FIFO)
                .totalQuantity(totalQuantity)
                .issuedQuantity(issuedQuantity)
                .discountAmount(DefaultAmount.DISCOUNT_AMOUNT.getAmount())
                .minAvailableAmount(DefaultAmount.MIN_AVAILABLE_AMOUNT.getAmount())
                .issueStartDateTime(now)
                .issueEndDateTime(end)
                .build();
    }

    public CouponEntity newDefaultCouponWithExpiration(
        String title,
        LocalDateTime issueStartDateTime,
        LocalDateTime issueEndDateTime
    ){
        return CouponEntity.defaultBuilder()
                .title(title)
                .couponAssignType(CouponAssignType.FIFO)
                .totalQuantity(DefaultQuantity.TOTAL_QUANTITY.getQuantity())
                .issuedQuantity(DefaultQuantity.ISSUED_QUANTITY.getQuantity())
                .discountAmount(DefaultAmount.DISCOUNT_AMOUNT.getAmount())
                .minAvailableAmount(DefaultAmount.MIN_AVAILABLE_AMOUNT.getAmount())
                .issueStartDateTime(issueStartDateTime)
                .issueEndDateTime(issueEndDateTime)
                .build();
    }

    public CouponEntity newDefaultCouponWithExpiration(
            String title,
            BigDecimal issuedQuantity
    ){
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime end = now.plus(DefaultDuration.THOUSAND_DAY.getTemporalAmount());

        return CouponEntity.defaultBuilder()
                .title(title)
                .couponAssignType(CouponAssignType.FIFO)
                .totalQuantity(DefaultQuantity.TOTAL_QUANTITY.getQuantity())
                .issuedQuantity(issuedQuantity)
                .discountAmount(DefaultAmount.DISCOUNT_AMOUNT.getAmount())
                .minAvailableAmount(DefaultAmount.MIN_AVAILABLE_AMOUNT.getAmount())
                .issueStartDateTime(now)
                .issueEndDateTime(end)
                .build();
    }

    public CouponEntity newDefaultCouponWithExpiration(
            String title,
            BigDecimal issuedQuantity,
            LocalDateTime issueStartDateTime, LocalDateTime issueEndDateTime
    ){
        return CouponEntity.defaultBuilder()
                .title(title)
                .couponAssignType(CouponAssignType.FIFO)
                .totalQuantity(DefaultQuantity.TOTAL_QUANTITY.getQuantity())
                .issuedQuantity(issuedQuantity)
                .discountAmount(DefaultAmount.DISCOUNT_AMOUNT.getAmount())
                .minAvailableAmount(DefaultAmount.MIN_AVAILABLE_AMOUNT.getAmount())
                .issueStartDateTime(issueStartDateTime)
                .issueEndDateTime(issueEndDateTime)
                .build();
    }

    public CouponEntity newDefaultCouponWithExpiration(
        String title,
        BigDecimal totalQuantity, BigDecimal issuedQuantity,
        LocalDateTime issueStartDateTime, LocalDateTime issueEndDateTime
    ){
        return CouponEntity.defaultBuilder()
                .title(title)
                .couponAssignType(CouponAssignType.FIFO)
                .totalQuantity(totalQuantity)
                .issuedQuantity(issuedQuantity)
                .discountAmount(DefaultAmount.DISCOUNT_AMOUNT.getAmount())
                .minAvailableAmount(DefaultAmount.MIN_AVAILABLE_AMOUNT.getAmount())
                .issueStartDateTime(issueStartDateTime)
                .issueEndDateTime(issueEndDateTime)
                .build();
    }

}
