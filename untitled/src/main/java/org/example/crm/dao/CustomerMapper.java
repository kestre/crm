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

    // 获得近一个月新增客户数量
    int queryNewCustomerCount();

    //  获得合作客户数量
    int queryCooperateCus();
}