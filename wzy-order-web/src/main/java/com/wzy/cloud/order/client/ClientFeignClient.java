package com.wzy.cloud.order.client;

import com.wzy.cloud.order.entity.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// @FeignClient 注解指定了要调用的服务名 (即 application.yml 里的 name)
@FeignClient(value = "wzy-client-service")
public interface ClientFeignClient {

    // 这里直接复制 ClientController 里的方法签名
    // 注意：@PathVariable("id") 中的 "id" 不能省略，否则会报错！
    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    public Client findById(@PathVariable("id") Integer id);
}