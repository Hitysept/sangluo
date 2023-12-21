package cn.sangluo.oauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName OauthServerApplication
 * @Description feign接口调试
 * @Author 月痕江离
 * @Date 2023/5/27 10:19
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OauthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication.class,args);
    }
}
