package com.wzy.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    // --- 核心步骤：注入 RestTemplate ---
    // 这一步相当于初始化了一个 HTTP 客户端工具，让我们可以发请求
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}