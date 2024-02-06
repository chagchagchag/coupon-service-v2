package io.chagchagchag.project.coupon.core.dataaccess.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCouponIssueEntity is a Querydsl query type for CouponIssueEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouponIssueEntity extends EntityPathBase<CouponIssueEntity> {

    private static final long serialVersionUID = -1915186070L;

    public static final QCouponIssueEntity couponIssueEntity = new QCouponIssueEntity("couponIssueEntity");

    public final QBaseDateTimeEntity _super = new QBaseDateTimeEntity(this);

    public final NumberPath<Long> couponId = createNumber("couponId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDateTime = _super.createdDateTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> issuedDateTime = createDateTime("issuedDateTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDateTime = _super.updatedDateTime;

    public final DateTimePath<java.time.LocalDateTime> usedDateTime = createDateTime("usedDateTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QCouponIssueEntity(String variable) {
        super(CouponIssueEntity.class, forVariable(variable));
    }

    public QCouponIssueEntity(Path<? extends CouponIssueEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCouponIssueEntity(PathMetadata metadata) {
        super(CouponIssueEntity.class, metadata);
    }

}

