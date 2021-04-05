package org.example.crm.query;

import org.example.crm.base.BaseQuery;

public class CustomerReprieveQuery extends BaseQuery {
    private Integer lossId;

    public Integer getLossId() {
        return lossId;
    }

    public void setLossId(Integer lossId) {
        this.lossId = lossId;
    }
}
