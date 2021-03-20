package org.example.crm.query;

import org.example.crm.base.BaseQuery;

public class SaleChanceQuery extends BaseQuery {

    private String customerName;
    private String createMan;
    private Integer state;  //0=未分配  1=已分配

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createrMan) {
        this.createMan = createrMan;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
