package com.wzy.cloud.order.controller;

import com.wzy.cloud.order.client.ClientFeignClient;
import com.wzy.cloud.order.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    // 【新增】定义日志对象
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private ClientFeignClient clientFeignClient;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createOrder() {
        // 【新增】打印日志，对应教材中的 log.info("开始调用order方法...")
        log.info("开始调用 createOrder 方法...");

        Integer clientId = 4;

        // 调用远程服务
        Client client = clientFeignClient.findById(clientId);

        // 打印日志
        System.out.println("==> 收到客户数据: " + client.getClientName());

        return "订单创建成功！(OpenFeign+Hystrix)\n" +
                "所属客户: " + client.getClientName() + "\n" +
                "备注信息: " + client.getContactPhone();
    }
}