package org.example.crm.dao;

import org.example.crm.base.BaseMapper;
import org.example.crm.vo.Customer;

import java.util.List;

public interface CustomerMapper extends BaseMapper<Customer, Integer> {
    List<String> getLevels();

    Customer queryCustomerByName(String name);

    List<Customer> queryLossCustomers();

    int updateCustomerStateByIds(List<Integer> lossCustomerIds);
}