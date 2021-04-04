package org.example.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.crm.base.BaseService;
import org.example.crm.dao.CustomerOrderMapper;
import org.example.crm.query.CustomerOrderQuery;
import org.example.crm.vo.CustomerOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerOrderService extends BaseService<CustomerOrder, Integer> {

    @Resource
    private CustomerOrderMapper customerorderMapper;

    public Map<String, Object> queryCustomerOrderByParams(CustomerOrderQuery customerOrderQuery){
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(customerOrderQuery.getPage(), customerOrderQuery.getLimit());
        PageInfo<CustomerOrder> pageInfo = new PageInfo<>(customerorderMapper.selectByParams(customerOrderQuery));

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());

        map.put("data", pageInfo.getList());

        return map;
    }

    public Map<String, Object> queryOrderById(Integer orderId) {
        return customerorderMapper.queryOrderById(orderId);
    }
}
