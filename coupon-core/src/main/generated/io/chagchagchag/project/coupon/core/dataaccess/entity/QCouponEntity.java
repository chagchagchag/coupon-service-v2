package io.chagchagchag.project.coupon.core.dataaccess.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCouponEntity is a Querydsl query type for CouponEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouponEntity extends EntityPathBase<CouponEntity> {

    private static final long serialVersionUID = -1815918379L;

    public static final QCouponEntity couponEntity = new QCouponEntity("couponEntity");

    public final QBaseDateTimeEntity _super = new QBaseDateTimeEntity(this);

    public final EnumPath<io.chagchagchag.project.coupon.core.model.CouponAssignType> couponAssignType = createEnum("couponAssignType", io.chagchagchag.project.coupon.core.model.CouponAssignType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDateTime = _super.createdDateTime;

    public final NumberPath<java.math.BigDecimal> discountAmount = createNumber("discountAmount", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> issuedQuantity = createNumber("issuedQuantity", java.math.BigDecimal.class);

    public final DateTimePath<java.time.LocalDateTime> issueEndDateTime = createDateTime("issueEndDateTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> issueStartDateTime = createDateTime("issueStartDateTime", java.time.LocalDateTime.class);

    public final NumberPath<java.math.BigDecimal> minAvailableAmount = createNumber("minAvailableAmount", java.math.BigDecimal.class);

    public final StringPath title = createString("title");

    public final NumberPath<java.math.BigDecimal> totalQuantity = createNumber("totalQuantity", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDateTime = _super.updatedDateTime;

    public QCouponEntity(String variable) {
        super(CouponEntity.class, forVariable(variable));
    }

    public QCouponEntity(Path<? extends CouponEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCouponEntity(PathMetadata metadata) {
        super(CouponEntity.class, metadata);
    }

}

