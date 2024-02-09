package io.chagchagchag.project.coupon.api.dataaccess;

import io.chagchagchag.project.coupon.api.dataaccess.repository.CouponJpaRepository;
import io.chagchagchag.project.coupon.api.dataaccess.valueobject.CouponIssueEntityDto;
import io.chagchagchag.project.coupon.api.fixtures.CouponEntityFixtures;
import io.chagchagchag.project.coupon.api.fixtures.CouponIssueEntityFixtures;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponIssueEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.factory.CouponIssueEntityFactory;
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

    @Autowired
    private CouponDataAccessService couponDataAccessService;

    @Autowired
    private CouponJpaRepository couponJpaRepository;

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

    ////// 2) issue 테스트 (saveNewCouponIssue() 를 감싸는 issue() 메서드를 검증)
    @Test
    @DisplayName("issue() : CouponEntity 에 문제가 없을 경우 issue() 를 정상적으로 완료한다")
    public void issue__COMPLETE_ISSUE_WHEN_COUPON_ENTITY_IS_VALID(){
        Long couponId = 1L;
        Long userId = 1L;

        CouponEntity coupon = CouponEntityFixtures.ofNoError(couponId);
        couponJpaRepository.save(coupon);

        CouponIssueEntity couponIssueEntity = CouponIssueEntityFixtures.ofNoError(couponId, userId);
        couponIssueDataAccessService.saveNewCouponIssue(couponId, userId);

        // when : 이미 존재하는 CouponIssueEntity 에 대해 Error 를 올바르게 내는지를 검증
        CouponIssueException exception = Assertions.assertThrows(CouponIssueException.class, () -> {
            couponIssueDataAccessService.issue(couponId, userId);
        });

        // then : error 검증
        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.DUPLICATED_COUPON_ISSUE_REQUEST);
    }

    @Test
    @DisplayName("issue() : 발급 수량에 무제가 있으면 예외를 throw")
    public void issue__THROW_ERROR_WHEN_ISSUE_QUANTITY_HAS_ERROR(){
        // given
        Long userId = 1L;
        CouponEntity coupon = CouponEntityFixtures.ofInvalidQuantity1();

        couponJpaRepository.save(coupon);

        // when
        CouponIssueException exception = Assertions.assertThrows(CouponIssueException.class, () -> {
            couponIssueDataAccessService.issue(coupon.getId(), userId);
        });

        // then
        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.EXCEEDED_COUPON_ISSUE_QUANTITY);
    }

    @Test
    @DisplayName("issue() : 발급 기한에 문제가 있으면 예외를 throw")
    public void issue__THROW_ERROR_WHEN_ISSUE_DATES_HAVE_ERROR(){
        // given
        Long userId = 1L;
        CouponEntity coupon = CouponEntityFixtures.ofInvalidIssueDate1();

        couponJpaRepository.save(coupon);

        // when
        CouponIssueException exception = Assertions.assertThrows(CouponIssueException.class, () -> {
            couponIssueDataAccessService.issue(coupon.getId(), userId);
        });

        // then
        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.UNAVAILABLE_COUPON_ISSUE_DATE);
    }

    @Test
    @DisplayName("issue() : 쿠폰이 존재하지 않을 경우 예외를 반환")
    public void issue__THROW_ERROR_WHEN_COUPON_NOT_EXIST(){
        Long userId = 1L;
        Long couponId = 1L;

        CouponIssueException exception = Assertions.assertThrows(CouponIssueException.class, () -> {
            couponIssueDataAccessService.issue(couponId, userId);
        });

        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.COUPON_NOT_EXIST);
    }
}
