package cn.sangluo.oauthserver.config;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName SaTokenConfigure
 * @Description TODO
 * @Author 月痕江离
 * @Date 2023/5/27 20:36
 * @Version 1.0
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    /**
     * Sa-Token 整合 jwt
     */
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }
}
