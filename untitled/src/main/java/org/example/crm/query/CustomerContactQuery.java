package org.example.crm.query;

import org.example.crm.base.BaseQuery;

public class CustomerContactQuery extends BaseQuery {

    private Integer cusId; //所属客户

    private String customer; //客户名称

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
