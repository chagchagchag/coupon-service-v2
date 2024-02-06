-- 참고: 쿠폰 발급과 쿠폰 생성은 다르다.
-- 쿠폰을 '생성'해야만 1000000개의 유저에게 '발급'하는 것이 가능하다.

-- 쿠폰
-- 최대 수량, 현재 발급 갯수 등등
CREATE TABLE coupon.coupons(
    id                      BIGINT(20)      NOT NULL    AUTO_INCREMENT,
    title                   VARCHAR(255)    NOT NULL    COMMENT '쿠폰 이름',
    coupon_assign_type      VARCHAR(255)    NOT NULL    COMMENT '쿠폰 발급 정책',
    total_quantity          INT             NULL        COMMENT '쿠폰 발급 최대 허용 수량',
    issued_quantity         INT             NOT NULL    COMMENT '현재 발급된 쿠폰 갯수',
    discount_amount         INT             NOT NULL    COMMENT '할인 금액',
    min_available_amount    INT             NOT NULL    COMMENT '최소 사용 금액',
    issue_start_datetime    datetime(6)     NOT NULL    COMMENT '쿠폰 발급 시각',
    issue_end_datetime      datetime(6)     NOT NULL    COMMENT '쿠폰 발급 종료 시각',
    created_datetime        datetime(6)     NOT NULL    COMMENT '쿠폰 생성 시각',
    updated_datetime        datetime(6)     NOT NULL    COMMENT '쿠폰 수정 시각',
    PRIMARY KEY (id)
) ENGINE = InnoDB
default charset = utf8mb4;


-- 발급 쿠폰 개별 데이터
CREATE TABLE coupon.coupon_issues(
    id                      BIGINT(20)      NOT NULL    AUTO_INCREMENT,
    coupon_id               BIGINT(20)      NOT NULL    COMMENT '쿠폰 ID',
    user_id                 BIGINT(20)      NOT NULL    COMMENT '유저 ID',
    issued_datetime         DATETIME(6)     NOT NULL    COMMENT '발급 시각',
    used_datetime           DATETIME(6)     NOT NULL    COMMENT '사용 시각',
    created_datetime        DATETIME(6)     NOT NULL    COMMENT '생성 시각',
    updated_datetime        DATETIME(6)     NOT NULL    COMMENT '수정 시각',
    PRIMARY KEY (id)
) ENGINE = InnoDB
default charset = utf8mb4;