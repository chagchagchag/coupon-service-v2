package io.chagchagchag.project.coupon.core.dataaccess.entity.factory;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

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

    }

    @Test
    @DisplayName("isIssueDateAvailable() : 발급 기간이 종료된 경우 false 를 리턴")
    void isIssueDateAvailable__RETURN_FALSE_WHEN_ISSUE_DATE_IS_ABNORMAL_STATE(){

    }

    @Test
    @DisplayName("issue() : 발급 수량, 발급 기간이 유효하면 발급 성공")
    void issue__ISSUE_SUCCESS_WHEN_QUANTITY_AND_DURATION_IS_NORMAL(){

    }

    @Test
    @DisplayName("issue() : 발급 기간이 유효해도 발급 수량을 초과하면 예외 throw")
    void issue__ISSUE_EXCEPTION_THROW_WHEN_DURATION_NORMAL_BUT_QUANTITY_UNAVAILABLE(){

    }

    @Test
    @DisplayName("issue() : 발급 기간이 비정상이면 예외를 throw")
    void issue__ISSUE_EXCEPTION_THROW_WHEN_DURATION_ABNORMAL(){

    }

    ///// 3) 발급 기한 만료 검증 메서드 테스트
    @Test
    @DisplayName("isExpiredCoupon() : 발급 기간이 종료된 경우 isExpiredCoupon() 은 true 를 리턴한다.")
    void isExpiredCoupon__WHEN_ISSUE_EXPIRED_THEN_RETURN_TRUE(){

    }

    ///// 4) isCouponQuantityFull


}
