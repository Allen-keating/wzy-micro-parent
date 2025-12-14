package com.wzy.cloud.client.service;

import com.wzy.cloud.client.dao.ClientDao;
import com.wzy.cloud.client.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    // 查询所有客户
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    // 根据ID查询客户
    public Client findById(Integer id) {
        // .get() 是为了从 Optional 容器中取出对象，如果不存在会抛异常
        return clientDao.findById(id).get();
    }

    // 添加客户
    public void add(Client client) {
        clientDao.save(client);
    }

    // 修改客户 (JPA中save方法如果ID存在即为修改)
    public void update(Client client) {
        clientDao.save(client);
    }

    // 根据ID删除客户
    public void deleteById(Integer id) {
        clientDao.deleteById(id);
    }
}