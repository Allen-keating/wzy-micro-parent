package com.wzy.cloud.order.controller;

import com.wzy.cloud.order.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance; // 务必导入这个包
import org.springframework.cloud.client.discovery.DiscoveryClient; // 务必导入这个包
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    // --- 新增：注入服务发现工具 ---
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createOrder() {
        // 假设我们要查询 ID 为 2 的客户（请确保数据库里有这条数据）
        Integer clientId = 2;

        // --- 核心修改步骤 ---

        // 1. 问 Eureka：“名为 WZY-CLIENT-SERVICE 的服务在哪里？”
        // getInstances 参数必须是你 Eureka 界面上显示的服务名 (通常是大写)
        List<ServiceInstance> instances = discoveryClient.getInstances("WZY-CLIENT-SERVICE");

        // 判空保护
        if (instances == null || instances.isEmpty()) {
            return "错误：在 Eureka 中找不到客户服务！";
        }

        // 2. 获取列表中的第一个实例 (暂时不考虑负载均衡)
        ServiceInstance serviceInstance = instances.get(0);

        // 3. 动态拼接 URL
        // 结果类似：http://192.168.x.x:9001/client/2
        String baseUrl = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        String url = baseUrl + "/client/" + clientId;

        // 打印一下日志，方便你观察它到底发现了什么地址
        System.out.println("==> 从 Eureka 发现的服务地址: " + url);

        // 4. 发送请求
        Client client = restTemplate.getForObject(url, Client.class);

        System.out.println("==> 正在为客户创建订单: " + client.getClientName());

        return "订单创建成功！(基于Eureka服务发现)\n" +
                "所属客户: " + client.getClientName() + "\n" +
                "客户电话: " + client.getContactPhone();
    }
}