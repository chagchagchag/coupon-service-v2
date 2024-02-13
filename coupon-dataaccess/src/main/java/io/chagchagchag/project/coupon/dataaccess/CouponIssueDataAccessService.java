package io.chagchagchag.project.coupon.dataaccess;

import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.CouponIssueEntity;
import io.chagchagchag.project.coupon.core.dataaccess.entity.factory.CouponIssueEntityFactory;
import io.chagchagchag.project.coupon.core.exception.CouponIssueException;
import io.chagchagchag.project.coupon.core.exception.ErrorCode;
import io.chagchagchag.project.coupon.dataaccess.mysql.mapper.CouponIssueEntityMapper;
import io.chagchagchag.project.coupon.dataaccess.mysql.repository.CouponIssueJpaRepository;
import io.chagchagchag.project.coupon.dataaccess.mysql.repository.CouponIssueReadQuerydsl;
import io.chagchagchag.project.coupon.dataaccess.mysql.repository.CouponJpaRepository;
import io.chagchagchag.project.coupon.dataaccess.mysql.repository.CouponMysqlLockRepository;
import io.chagchagchag.project.coupon.dataaccess.mysql.valueobject.CouponIssueEntityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static io.chagchagchag.project.coupon.core.exception.ErrorCode.COUPON_NOT_EXIST;

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
        CouponIssueEntity issueEntity = couponIssueJpaRepository.findCouponIssue(couponId, userId);
        if(issueEntity != null)
            throw new CouponIssueException(ErrorCode.DUPLICATED_COUPON_ISSUE_REQUEST, ErrorCode.DUPLICATED_COUPON_ISSUE_REQUEST.message);
    }

    @Transactional(readOnly = true)
    public Optional<CouponIssueEntityDto> findCouponIssueById(Long couponIssueId){
        return couponIssueJpaRepository
                .findById(couponIssueId)
                .map(couponIssueEntityMapper::toCouponIssueEntityDto);
    }

    @Transactional
    public CouponEntity issue(Long couponId, Long userId){
        CouponEntity couponEntity = findCouponByCouponIdWithLock(couponId);
        couponEntity.issue();

        saveNewCouponIssue(couponId, userId);
        return couponEntity;
    }

    @Transactional(readOnly = true)
    public CouponEntity findCouponById(Long couponId){
        return couponJpaRepository
                .findById(couponId)
                .orElseThrow(() -> {
                    throw new CouponIssueException(COUPON_NOT_EXIST, COUPON_NOT_EXIST.message);
                });
    }

    @Transactional(readOnly = true)
    public CouponEntity findCouponByCouponIdWithLock(Long couponId){
        return Optional
                .ofNullable(couponMysqlLockRepository.findCouponWithLock(couponId))
                .orElseThrow(() -> {
                    throw new CouponIssueException(COUPON_NOT_EXIST, COUPON_NOT_EXIST.message);
                });
    }
}
