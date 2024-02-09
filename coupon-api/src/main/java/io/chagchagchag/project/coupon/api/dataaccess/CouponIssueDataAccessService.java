package io.chagchagchag.project.coupon.api.dataaccess;

import io.chagchagchag.project.coupon.api.dataaccess.mapper.CouponIssueEntityMapper;
import io.chagchagchag.project.coupon.api.dataaccess.repository.CouponIssueJpaRepository;
import io.chagchagchag.project.coupon.api.dataaccess.repository.CouponIssueReadQuerydsl;
import io.chagchagchag.project.coupon.api.dataaccess.repository.CouponJpaRepository;
import io.chagchagchag.project.coupon.api.dataaccess.repository.CouponMysqlLockRepository;
import io.chagchagchag.project.coupon.api.dataaccess.valueobject.CouponIssueEntityDto;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponIssueEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.factory.CouponIssueEntityFactory;
import io.chagchagchag.project.coupon.core.exception.CouponIssueException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static io.chagchagchag.project.coupon.core.exception.ErrorCode.COUPON_NOT_EXIST;
import static io.chagchagchag.project.coupon.core.exception.ErrorCode.DUPLICATED_COUPON_ISSUE_REQUEST;

@RequiredArgsConstructor
@Service
public class CouponIssueDataAccessService {
    private final CouponIssueJpaRepository couponIssueJpaRepository;
    private final CouponIssueReadQuerydsl couponIssueReadQuerydsl;
    private final CouponIssueEntityMapper couponIssueEntityMapper;
    private final CouponIssueEntityFactory couponIssueEntityFactory;
    private final CouponMysqlLockRepository couponMysqlLockRepository;
    private final CouponJpaRepository couponJpaRepository;

    @Transactional
    public CouponIssueEntityDto saveNewCouponIssue(Long couponId, Long userId){
        validateIssueNotExists(couponId, userId);
        CouponIssueEntity issueEntity = couponIssueJpaRepository.save(couponIssueEntityFactory.newDefaultCouponIssue(couponId, userId));
        return couponIssueEntityMapper.toCouponIssueEntityDto(issueEntity);
    }

    private void validateIssueNotExists(Long couponId, Long userId){
        CouponIssueEntity issueEntity = couponIssueReadQuerydsl.findFirstCouponIssue(couponId, userId);
        if(issueEntity != null)
            throw new CouponIssueException(DUPLICATED_COUPON_ISSUE_REQUEST, DUPLICATED_COUPON_ISSUE_REQUEST.message);
    }

    @Transactional(readOnly = true)
    public Optional<CouponIssueEntityDto> findById(Long couponIssueId){
        return couponIssueJpaRepository
                .findById(couponIssueId)
                .map(couponIssueEntityMapper::toCouponIssueEntityDto);
    }

    @Transactional
    public void issue(Long couponId, Long userId){
        CouponEntity couponEntity = findCouponByCouponIdWithLock(couponId);
        couponEntity.issue();

        saveNewCouponIssue(couponId, userId);
        // TODO :: Cache Aside 자료구조 put
    }

    @Transactional(readOnly = true)
    public CouponEntity findCouponByCouponIdWithLock(Long couponId){
        return couponMysqlLockRepository
                .findCouponWithLock(couponId)
                .orElseThrow(() -> {
                    throw new CouponIssueException(COUPON_NOT_EXIST, COUPON_NOT_EXIST.message);
                });
    }
}
