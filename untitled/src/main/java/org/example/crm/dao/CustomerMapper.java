package org.example.crm.dao;

import org.example.crm.base.BaseMapper;
import org.example.crm.query.CustomerQuery;
import org.example.crm.vo.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerMapper extends BaseMapper<Customer, Integer> {
    List<String> getLevels();

    Customer queryCustomerByName(String name);

    List<Customer> queryLossCustomers();

    int updateCustomerStateByIds(List<Integer> lossCustomerIds);
    //    查询客户贡献
    List<Map<String, Object>> queryCustomerContributionByParams(CustomerQuery customerQuery);

    // 查询客户级别构成
    List<Map<String, Object>> countCustomerLevelGroup();
}