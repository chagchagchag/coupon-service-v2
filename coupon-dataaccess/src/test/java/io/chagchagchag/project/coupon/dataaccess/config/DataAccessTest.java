package io.chagchagchag.project.coupon.dataaccess.config;

import io.chagchagchag.project.coupon.dataaccess.CouponDataAccessConfig;
import io.chagchagchag.project.coupon.dataaccess.mysql.repository.CouponMysqlLockRepository;
import io.chagchagchag.project.coupon.dataaccess.mysql.repository.CouponReadQuerydsl;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.factory.CouponEntityFactory;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.config.name=application-dataaccess")
@SpringBootTest(classes = CouponDataAccessConfig.class)
public class DataAccessTest {

    @Autowired
    private CouponMysqlLockRepository couponMysqlLockRepository;

    @Autowired
    private CouponReadQuerydsl couponReadQuerydsl;

    @Autowired
    private CouponEntityFactory couponEntityFactory;

    @BeforeEach
    public void init(){
        couponMysqlLockRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("데이터 저장 테스트")
    public void saveTest(){
        CouponEntity couponEntity = couponEntityFactory.newDefaultCoupon(
            "농심 30% 할인 이벤트",
                BigDecimal.valueOf(10000)
        );

        couponMysqlLockRepository.save(couponEntity);

        CouponEntity coupon = couponReadQuerydsl.findCouponById(couponEntity.getId());

        assertThat(coupon).isNotNull();
    }

}
