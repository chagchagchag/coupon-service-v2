package io.chagchagchag.project.coupon.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean(name = "defaultObjectMapper")
    public ObjectMapper defaultObjectMapper(){
        // TODO |
        //  숫자 처리 등등 커스텀 설정이 필요한데 프로젝트 구조 작업을 마친 후에 하기 위해 잠시 보류
        return new ObjectMapper();
    }
}
