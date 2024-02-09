package io.chagchagchag.project.coupon.core.dataaccess.entity.factory;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import io.chagchagchag.project.coupon.core.exception.CouponIssueException;
import io.chagchagchag.project.coupon.core.exception.ErrorCode;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Transactional
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.config.name=application-core")
@SpringBootTest
public class CouponEntityFactoryTest {

    @Autowired
    private CouponEntityFactory couponEntityFactory;

    ///// 1) 발급 수량 테스트
    @Test
    @DisplayName("hasEnoughQuantity() : 발급 수량이 남아있을때 신규발급을 한다면 true 를 반환한다.")
    void hasEnoughQuantity__RETURN_TRUE_WHEN_COUPON_HAS_ENOUGH_QUANTITY(){
        // given
        CouponEntity coupon = couponEntityFactory.newDefaultCoupon("농심 10% 할인 이벤트", BigDecimal.TWO, BigDecimal.ONE);

        // when
        final boolean hasEnoughQuantity = coupon.hasEnoughQuantity();

        // then
        Assertions.assertTrue(hasEnoughQuantity);
    }

    @Test
    @DisplayName("hasEnoughQuantity() : 발급 수량이 더 남아있지 않을 경우 false 를 반환한다.")
    void hasEnoughQuantity__RETURN_FALSE_WHEN_COUPON_QUANTITY_IS_FULL(){
        // given
        CouponEntity coupon = couponEntityFactory.newDefaultCoupon("농심 10% 할인 이벤트", BigDecimal.TWO, BigDecimal.TWO);

        // when
        final boolean hasEnoughQuantity = coupon.hasEnoughQuantity();

        // then
        Assertions.assertFalse(hasEnoughQuantity);
    }

    @Test
    @DisplayName("hasEnoughQuantity() : 무한으로 발급 가능한 쿠폰일 경우 true 를 반환한다.")
    void hasEnoughQuantity__RETURN_TRUE_WHEN_COUPON_HAS_INFINITE_QUANTITY(){
        // given
        CouponEntity coupon = couponEntityFactory.newInfiniteCoupon("농심 10% 할인 이벤트");

        // when
        final boolean hasEnoughQuantity = coupon.hasEnoughQuantity();

        // then
        Assertions.assertTrue(hasEnoughQuantity);
    }

    ///// 2) 발급 기간 테스트
    @Test
    @DisplayName("isIssueDateAvailable() : 발급 기간이 정상일 경우 true 를 리턴")
    void isIssueDateAvailable__RETURN_TRUE_WHEN_ISSUE_DATE_IS_NORMAL_STATE(){
        // given
        CouponEntity coupon = couponEntityFactory
                .newDefaultCouponWithExpiration(
                        "농심 10% 할인 이벤트",
                        LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(2)
                );

        // when
        final boolean isIssueDateAvailable = coupon.isIssueDateAvailable();

        // then
        Assertions.assertTrue(isIssueDateAvailable);
    }

    @Test
    @DisplayName("isIssueDateAvailable() : 발급 기간이 종료된 경우 false 를 리턴")
    void isIssueDateAvailable__RETURN_FALSE_WHEN_ISSUE_DATE_IS_ABNORMAL_STATE(){
        // given
        CouponEntity coupon = couponEntityFactory
                .newDefaultCouponWithExpiration(
                        "농심 10% 할인 이벤트",
                        LocalDateTime.now().minusDays(2),
                        LocalDateTime.now().minusDays(1)
                );

        // when
        final boolean isIssueDateAvailable = coupon.isIssueDateAvailable();

        // then
        Assertions.assertFalse(isIssueDateAvailable);
    }

    @Test
    @DisplayName("issue() : 발급 수량, 발급 기간이 유효하면 발급 성공")
    void issue__ISSUE_SUCCESS_WHEN_QUANTITY_AND_DURATION_IS_NORMAL(){
        // given
        CouponEntity coupon = couponEntityFactory
                .newDefaultCouponWithExpiration(
                    "농심 10% 할인 이벤트",
                    BigDecimal.TWO, BigDecimal.ONE,
                    LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1)
                );

        // when
        coupon.issue();
        // then
        Assertions.assertEquals(coupon.getIssuedQuantity(), BigDecimal.ONE);
    }

    @Test
    @DisplayName("issue() : 발급 기간이 유효해도 발급 수량을 초과하면 예외 throw")
    void issue__ISSUE_EXCEPTION_THROW_WHEN_DURATION_NORMAL_BUT_QUANTITY_UNAVAILABLE(){
        // given
        CouponEntity coupon = couponEntityFactory
                .newDefaultCouponWithExpiration(
                        "농심 10% 할인 이벤트",
                        BigDecimal.ONE, BigDecimal.TWO,
                        LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1)
                );

        // when, then
        CouponIssueException exception = Assertions.assertThrows(CouponIssueException.class, coupon::issue);
        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.EXCEEDED_COUPON_ISSUE_QUANTITY);
    }

    @Test
    @DisplayName("issue() : 발급 기간이 비정상이면 예외를 throw")
    void issue__ISSUE_EXCEPTION_THROW_WHEN_DURATION_ABNORMAL(){
        // given
        CouponEntity coupon = couponEntityFactory
                .newDefaultCouponWithExpiration(
                        "농심 10% 할인 이벤트",
                        BigDecimal.TWO, BigDecimal.ONE,
                        LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2)
                );

        // when, then
        CouponIssueException exception = Assertions.assertThrows(CouponIssueException.class, coupon::issue);
        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.UNAVAILABLE_COUPON_ISSUE_DATE);
    }

    ///// 3) isCouponQuantityFull


}
