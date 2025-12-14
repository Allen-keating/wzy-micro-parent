package com.wzy.cloud.order.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 客户实体类 (原User类修改版)
 * 对应数据库表: tb_client
 */
@Entity
@Table(name = "tb_client") // 修改表名为 tb_client
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 客户编号

    @Column(name = "client_name")
    private String clientName; // 客户名称 (原username)

    @Column(name = "contact_phone")
    private String contactPhone; // 联系电话 (原password字段位置，改为电话更合理)

    @Column(name = "client_type")
    private String clientType; // 客户类型，如"VIP" (原sex)

    @Column(name = "account_balance")
    private Double accountBalance; // 账户余额 (原money)

    // 无参构造
    public Client() {
    }

    // 全参构造
    public Client(Integer id, String clientName, String contactPhone, String clientType, Double accountBalance) {
        this.id = id;
        this.clientName = clientName;
        this.contactPhone = contactPhone;
        this.clientType = clientType;
        this.accountBalance = accountBalance;
    }

    // --- Getter 和 Setter 方法 (必不可少) ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }
}