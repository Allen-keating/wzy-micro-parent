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

    // --- 注入 Feign 接口 ---
    @Autowired
    private ClientFeignClient clientFeignClient;

    /**
     * 购票方法 (使用 OpenFeign 版本)
     * 代码非常简洁，不仅实现了远程调用，还自动集成了负载均衡
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createOrder() {
        Integer clientId = 2; // 确保数据库里有这个ID

        // --- 核心步骤：像调用本地方法一样调用远程微服务 ---
        Client client = clientFeignClient.findById(clientId);

        System.out.println("==> 正在为客户创建订单: " + client.getClientName());

        return "订单创建成功！(OpenFeign调用)\n" +
                "所属客户: " + client.getClientName() + "\n" +
                "客户电话: " + client.getContactPhone();
    }
}