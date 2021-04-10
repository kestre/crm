package org.example.crm.query;

import org.example.crm.base.BaseQuery;

public class CustomerQuery extends BaseQuery {

    private String customerName;    //客户名字

    private String cusNum;  //客户编号

    private String level;   //客户级别

    private String type;   // 金额区间  1=1-1000 2=1000-3000 3=3000-5000 4=5000+

    private String time;    //订单时间

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
