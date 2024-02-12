package io.chagchagchag.project.coupon.api.cache.redis.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.chagchagchag.project.coupon.core.exception.CouponIssueException;
import io.chagchagchag.project.coupon.core.cache.redis.RedisKey;
import io.chagchagchag.project.coupon.core.cache.redis.mapper.CouponIssueRedisTaskDtoMapper;
import io.chagchagchag.project.coupon.core.cache.redis.resultcode.CouponIssueEnqueueResultCode;
import io.chagchagchag.project.coupon.core.cache.redis.task.CouponIssueRedisTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

import static io.chagchagchag.project.coupon.core.exception.ErrorCode.COUPON_ISSUE_FAIL;

@RequiredArgsConstructor
@Repository
public class CouponIssueRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final CouponIssueRedisTaskDtoMapper couponIssueRedisTaskDtoMapper;

    @Qualifier("defaultObjectMapper")
    private final ObjectMapper objectMapper;
    private final RedisScript<String> redisScript = enqueueScript();

    public void enqueueCouponIssueRequest(Long couponId, Long userId, BigDecimal totalIssueQuantity){
        final String issueKey = RedisKey.couponIssueRequestCouponKey(couponId);
        final String queueKey = RedisKey.couponIssueRequestQueueKey();
        CouponIssueRedisTaskDto couponIssueRedisTaskDto = couponIssueRedisTaskDtoMapper.fromCouponIdAndUserId(couponId, userId);

        try{
            String taskEnqueueResultCode = redisTemplate.execute(
                redisScript,
                List.of(issueKey, queueKey),
                String.valueOf(userId),
                String.valueOf(totalIssueQuantity),
                objectMapper.writeValueAsString(couponIssueRedisTaskDto)
            );

            CouponIssueEnqueueResultCode resultCode = CouponIssueEnqueueResultCode.valueOf(taskEnqueueResultCode);
            CouponIssueEnqueueResultCode.validateResultCode(resultCode);
        }
        catch(JsonProcessingException e){
            throw new CouponIssueException(COUPON_ISSUE_FAIL, COUPON_ISSUE_FAIL.message);
        }
    }

    private RedisScript<String> enqueueScript(){
        String script = """
                if redis.call('SISMEMBER', KEYS[1], ARGV[1]) == 1 then
                    return 'DUPLICATED_ENQUEUE_REQUEST'
                end
                
                if tonumber(ARGV[2]) > redis.call('SCARD', KEYS[1]) then
                    redis.call('SADD', KEYS[1], ARGV[1])
                    redis.call('RPUSH', KEYS[2], ARGV[3])
                    return 'SUCCESS'
                end
                
                return 'EXCEEDED_TOTAL_COUPON_ISSUE_LIMIT'
                """;

        return RedisScript.of(script, String.class);
    }

}
