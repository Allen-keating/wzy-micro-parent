package com.wzy.cloud.order.client;

import com.wzy.cloud.order.entity.Client;
import org.springframework.stereotype.Component;

@Component // 必须加这个注解，交给 Spring 管理
public class ClientFeignClientImpl implements ClientFeignClient {

    /**
     * 这里的代码就是“熔断”后执行的逻辑
     */
    @Override
    public Client findById(Integer id) {
        System.out.println(">>> 触发熔断降级：ClientFeignClientImpl.findById 执行");

        // 我们不返回 null，而是返回一个“默认客户”对象，防止空指针异常
        Client mockClient = new Client();
        mockClient.setId(-1);
        mockClient.setClientName("【熔断兜底】服务不可用");
        mockClient.setContactPhone("请稍后重试");
        return mockClient;
    }
}