package org.example.crm.query;

import org.example.crm.base.BaseQuery;

public class CustomerServeQuery extends BaseQuery {

    private String customer;
    private String serveType;
    private String state;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getServeType() {
        return serveType;
    }

    public void setServeType(String serveType) {
        this.serveType = serveType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
