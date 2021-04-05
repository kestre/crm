package org.example.crm.query;

import org.example.crm.base.BaseQuery;

public class CustomerLossQuery extends BaseQuery {
    private String cusNum;

    private String cusName;

    private Integer state;

    public String getCusNum() {
        return cusNum;
    }

    public void setCusNum(String cusNum) {
        this.cusNum = cusNum;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
