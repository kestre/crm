package org.example.crm.query;

import org.example.crm.base.BaseQuery;

public class CustomerQuery extends BaseQuery {

    private String customerName;

    private String cusNum;

    private String level;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCusNum() {
        return cusNum;
    }

    public void setCusNum(String cusNum) {
        this.cusNum = cusNum;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
