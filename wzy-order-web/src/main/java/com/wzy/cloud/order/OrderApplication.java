package com.wzy.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients; // 1. 导入包

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients // 2. 开启 Feign 功能
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    // RestTemplate 可以保留也可以删掉，Feign 底层其实默认还是用的 HTTP 客户端
    // @Bean
    // public RestTemplate restTemplate() {
    //     return new RestTemplate();
    // }
}