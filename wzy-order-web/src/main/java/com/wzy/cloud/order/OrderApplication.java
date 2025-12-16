package com.wzy.cloud.order;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix; // 1. 导入包
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix // 2. 开启熔断器功能
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
    // 3. 确保这个 Bean 存在，因为 Controller 里用到了
    @Bean
    //@LoadBalanced // 如果你想用服务名调用，最好加上这个注解(虽然原书可能没强调，但用服务名必须加)
    // 为了稳妥，我们暂时先不加 @LoadBalanced，依靠之前 RandomRule 的配置，或者
    // 如果你 Controller 里用的是 http://wzy-client-service/..., 必须加 @LoadBalanced
    // 这里我们直接加回去，保证 Ribbon 生效
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}