package com.wzy.cloud.client.dao;

import com.wzy.cloud.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 客户数据访问层接口
 * 继承 JpaRepository 后，自动拥有基本的增删改查功能
 */
public interface ClientDao extends JpaRepository<Client, Integer> {
    // 这是一个接口，不需要写实现类，Spring Data JPA 会自动代理
}