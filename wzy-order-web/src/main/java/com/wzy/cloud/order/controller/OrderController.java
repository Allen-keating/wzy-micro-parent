package com.wzy.cloud.order.controller;

import com.wzy.cloud.order.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient; // 务必导入这个包
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    // --- 变化点：注入 Ribbon 负载均衡客户端 ---
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 购票方法 (使用 Ribbon 版本)
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createOrder() {
        Integer clientId = 2;

        // --- 核心步骤 ---
        // 1. 使用 Ribbon 的 choose 方法，从 "wzy-client-service" 服务群中选一个实例
        // 默认策略是轮询 (Round Robin)
        ServiceInstance serviceInstance = loadBalancerClient.choose("wzy-client-service");

        if (serviceInstance == null) {
            return "错误：在 Eureka 中找不到可用的客户服务实例！";
        }

        // 2. 动态拼接 URL
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort()
                + "/client/" + clientId;

        System.out.println("==> Ribbon 选择的服务地址: " + url);

        // 3. 发送请求
        Client client = restTemplate.getForObject(url, Client.class);

        System.out.println("==> 正在为客户创建订单: " + client.getClientName());

        return "订单创建成功！(Ribbon负载均衡)\n" +
                "所属客户: " + client.getClientName() + "\n" +
                "服务端口: " + serviceInstance.getPort(); // 打印端口，验证是哪个服务干的活
    }
}