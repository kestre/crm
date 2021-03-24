package org.example.crm.query;

import org.example.crm.base.BaseQuery;

public class SaleChanceQuery extends BaseQuery {

    private String customerName;
    private String createMan;
    private Integer state;  //0=未分配  1=已分配

    private String devResult;
    private Integer assignMan;

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

    public String getDevResult() {
        return devResult;
    }

    public void setDevResult(String devResult) {
        this.devResult = devResult;
    }

    public Integer getAssignMan() {
        return assignMan;
    }

    public void setAssignMan(Integer assignMan) {
        this.assignMan = assignMan;
    }
}
