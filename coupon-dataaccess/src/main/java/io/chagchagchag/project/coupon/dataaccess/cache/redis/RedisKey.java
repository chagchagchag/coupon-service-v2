package io.chagchagchag.project.coupon.dataaccess.cache.redis;

public class RedisKey {
    // TODO: |
    //  RedisCouponIssueType, RedisOperation 과 함께 구조를 잡을 예정이긴 한데, 지금 당장은 구현 시간을 맞추기 위해 하드코딩을 함
    //  이 부분에 대해 꼭 리팩토링을 해야 함. 코드 이곳 저곳에 하드코딩이 섞이면 관리가 힘들어짐.
    //  https://www.baeldung.com/java-extending-enums

    public static String couponIssueRequestCouponKey(Long couponId){
        return "COUPON.ISSUE.REQUEST.COUPON_KEY=%s".formatted(couponId);
    }

    public static String couponIssueRequestQueueKey(){
        return "COUPON.ISSUE.REQUEST.QUEUE_KEY";
    }
}
