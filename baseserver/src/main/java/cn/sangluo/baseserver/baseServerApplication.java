package cn.sangluo.baseserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "cn.sangluo.baseserver.mapper")
public class baseServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(baseServerApplication.class, args);
    }
}
