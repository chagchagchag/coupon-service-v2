package io.chagchagchag.project.coupon.core.dataaccess.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "coupon_issues")
public class CouponIssueEntity extends BaseDateTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long couponId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, name = "issued_datetime")
    @CreatedDate
    private LocalDateTime issuedDateTime;

    @Column(name = "used_datetime")
    private LocalDateTime usedDateTime;

    @Builder(builderClassName = "DefaultBuilder", builderMethodName = "defaultBuilder")
    public CouponIssueEntity(
        Long id,
        Long couponId,
        Long userId,
        LocalDateTime issuedDateTime,
        LocalDateTime usedDateTime
    ){
        this.id = id;
        this.couponId = couponId;
        this.userId = userId;
        this.issuedDateTime = issuedDateTime;
        this.usedDateTime = usedDateTime;
    }

}
