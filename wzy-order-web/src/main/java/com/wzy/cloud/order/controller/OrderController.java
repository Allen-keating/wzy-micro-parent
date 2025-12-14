package com.wzy.cloud.order.controller;

import com.wzy.cloud.order.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order") // 路径改为 /order
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 创建订单方法
     * 逻辑：这就好比你在淘宝下单（Order服务），淘宝后台需要去查一下你的账号信息（Client服务）
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createOrder() {
        // 模拟当前下单的客户ID为 1
        Integer clientId = 2;

        // --- 核心代码：远程调用 ---
        // 使用 RestTemplate 访问另一个微服务的 URL
        // 注意：这里必须和我们之前定义的接口地址完全一致
        String url = "http://localhost:9001/client/" + clientId;

        // 发送 GET 请求，并把返回的 JSON 转换成 Client 对象
        Client client = restTemplate.getForObject(url, Client.class);

        System.out.println("==> 正在为客户创建订单: " + client.getClientName());

        // 返回一段话给浏览器
        return "订单创建成功！\n" +
                "所属客户: " + client.getClientName() + "\n" +
                "客户电话: " + client.getContactPhone();
    }
}