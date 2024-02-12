package io.chagchagchag.project.coupon.api;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@Transactional
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.config.name=application-api")
public class CouponApiApplicationTest {

    @Test
    public void contextLoads(){

    }

}
