package org.example.crm.dao;

import org.example.crm.base.BaseMapper;
import org.example.crm.vo.CustomerOrder;

import java.util.Map;

public interface CustomerOrderMapper extends BaseMapper<CustomerOrder, Integer> {
    Map<String, Object> queryOrderById(Integer orderId);

    CustomerOrder queryLossCustomerOrderByCustomerId(Integer customerId);

    //  获得近一个月新增合同数量
    int queryNewCustomerOrderCount();
}