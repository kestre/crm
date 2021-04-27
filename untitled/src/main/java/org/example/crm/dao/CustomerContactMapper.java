package org.example.crm.dao;

import org.example.crm.base.BaseMapper;
import org.example.crm.vo.CustomerContact;

public interface CustomerContactMapper extends BaseMapper<CustomerContact, Integer> {
    int queryRecentContactTime();
}