package io.chagchagchag.project.coupon.api.dataaccess;

import io.chagchagchag.project.coupon.api.dataaccess.valueobject.CouponIssueEntityDto;
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

@Transactional
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.config.name=application-api")
@SpringBootTest
public class CouponDataAccessServiceTest {

    @Autowired
    private CouponDataAccessService couponDataAccessService;




    ///// 2) findCouponByCouponIdWithLock(couponId)

}
