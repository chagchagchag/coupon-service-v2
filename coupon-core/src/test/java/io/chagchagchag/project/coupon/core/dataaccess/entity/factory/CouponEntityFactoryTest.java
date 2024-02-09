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

    @Test
    @DisplayName("발급 수량이 남아있을때 신규발급을 한다면 true 를 반환한다.")
    void RETURN_TRUE_WHEN_COUPON_HAS_ENOUGH_QUANTITY(){
        // given
        CouponEntity coupon = couponEntityFactory.newDefaultCoupon("농심 10% 할인 이벤트", BigDecimal.TWO, BigDecimal.ONE);

        // when
        final boolean hasEnoughQuantity = coupon.hasEnoughQuantity();

        // then
        Assertions.assertTrue(hasEnoughQuantity);
    }


}
