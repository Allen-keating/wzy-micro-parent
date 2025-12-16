package com.wzy.cloud.order.client;

import com.wzy.cloud.order.entity.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * OpenFeign 客户端接口
 * value = "wzy-client-service": 指定要调用的远程服务名称（必须和注册中心里的名字一致）
 * fallback = ClientFeignClientImpl.class: 指定熔断降级类，当调用失败时，会去执行这个类里的方法
 */
@FeignClient(value = "wzy-client-service", fallback = ClientFeignClientImpl.class)
public interface ClientFeignClient {

    /**
     * 远程调用方法定义
     * 这里的路径 /client/{id} 必须和 wzy-client-service 的 Controller 里的路径一致
     */
    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    public Client findById(@PathVariable("id") Integer id);
}