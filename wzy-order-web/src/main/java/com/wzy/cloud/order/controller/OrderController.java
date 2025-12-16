package com.wzy.cloud.order.controller;

import com.wzy.cloud.order.client.ClientFeignClient;
import com.wzy.cloud.order.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    // 注入 OpenFeign 接口
    @Autowired
    private ClientFeignClient clientFeignClient;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createOrder() {
        Integer clientId = 2;

        // 1. 调用远程服务 (如果服务挂了，会自动进入 ClientFeignClientImpl 的 fallback)
        Client client = clientFeignClient.findById(clientId);

        // 2. 打印日志
        System.out.println("==> 收到客户数据: " + client.getClientName());

        // 3. 返回结果
        return "订单创建成功！(OpenFeign+Hystrix)\n" +
                "所属客户: " + client.getClientName() + "\n" +
                "备注信息: " + client.getContactPhone();
    }
}