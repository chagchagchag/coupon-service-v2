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
public class CouponIssueDataAccessServiceTest {

    @Autowired
    private CouponIssueDataAccessService couponIssueDataAccessService;

    ///// 1) saveNewCouponIssue(couponId, userId)
    @Test
    @DisplayName("saveNewCouponIssue() : 이미 존재하는 쿠폰 발급권을 한번 더 발급하면 예외를 throw")
    public void saveNewCouponIssue__THROW_COUPON_ISSUE_WHEN_COUPON_ISSUE_ALREADY_EXISTS(){
        // given
        couponIssueDataAccessService.saveNewCouponIssue(1L, 1L);

        // when
        CouponIssueException exception = Assertions.assertThrows(CouponIssueException.class, () -> {
            couponIssueDataAccessService.saveNewCouponIssue(1L, 1L);
        });

        // then
        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.DUPLICATED_COUPON_ISSUE_REQUEST);
    }

    @Test
    @DisplayName("saveNewCouponIssue() : 정상적인 경우에 쿠폰발급에 성공한다.")
    public void saveNewCouponIssue__COUPON_ISSUE_SAVE_SUCCESS_WHEN_NORMAL_CASE(){

        // when
        CouponIssueEntityDto couponIssueEntityDto = couponIssueDataAccessService.saveNewCouponIssue(1L, 1L);

        // then
        Assertions.assertTrue(couponIssueDataAccessService.findById(couponIssueEntityDto.id()).isPresent());
    }
}
