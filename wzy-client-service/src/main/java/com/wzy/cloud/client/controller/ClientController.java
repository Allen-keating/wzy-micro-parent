package com.wzy.cloud.client.controller;

import com.wzy.cloud.client.entity.Client;
import com.wzy.cloud.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    // 【新增】定义日志对象
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Client> findAll() {
        return clientService.findAll();
    }

    // 根据ID查询
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Client findById(@PathVariable Integer id) {
        // 【新增】打印日志，对应教材中的 "进入了UserController的findById方法"
        log.info("进入了 ClientController 的 findById 方法");

        // 【新增】对应教材中的 System.out.println("用户微服务11111");
        System.out.println("客户微服务 11111");

        System.out.println(">>> 客户服务 [22222] 被调用了！");
        return clientService.findById(id);
    }

    // 其他方法保持不变...
    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody Client client) {
        clientService.add(client);
        return "客户添加成功";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(@RequestBody Client client, @PathVariable Integer id) {
        client.setId(id);
        clientService.update(client);
        return "客户信息修改成功";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable Integer id) {
        clientService.deleteById(id);
        return "客户删除成功";
    }
}