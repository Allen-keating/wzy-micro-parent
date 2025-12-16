package com.wzy.cloud.client.controller;

import com.wzy.cloud.client.entity.Client;
import com.wzy.cloud.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client") // 修改接口前缀为 /client
public class ClientController {

    @Autowired
    private ClientService clientService;

    // 1. 查询所有客户 (GET http://localhost:9001/client)
    @RequestMapping(method = RequestMethod.GET)
    public List<Client> findAll() {
        return clientService.findAll();
    }

    // 2. 根据ID查询 (GET http://localhost:9001/client/1)

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Client findById(@PathVariable Integer id) {
        // 添加这一行日志，用于区分实例
        System.out.println(">>> 客户服务 [22222] 被调用了！");
        return clientService.findById(id);
    }


    // 3. 添加客户 (POST http://localhost:9001/client)
    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody Client client) {
        clientService.add(client);
        return "客户添加成功";
    }

    // 4. 修改客户 (PUT http://localhost:9001/client/1)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(@RequestBody Client client, @PathVariable Integer id) {
        // 确保修改的是路径上指定的ID
        client.setId(id);
        clientService.update(client);
        return "客户信息修改成功";
    }

    // 5. 删除客户 (DELETE http://localhost:9001/client/1)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable Integer id) {
        clientService.deleteById(id);
        return "客户删除成功";
    }
}