package org.example.crm.query;

import org.example.crm.base.BaseQuery;

public class CustomerLinkmanQuery extends BaseQuery {
    private String linkName; //联系人名称

    private String gender; //性别

    private Integer cusId; //所属客户

    private String name; //客户名称

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
