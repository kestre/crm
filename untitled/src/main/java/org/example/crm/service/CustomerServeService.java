package org.example.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.crm.base.BaseService;
import org.example.crm.dao.CustomerServeMapper;
import org.example.crm.query.CustomerServeQuery;
import org.example.crm.vo.Customer;
import org.example.crm.vo.CustomerServe;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerServeService extends BaseService<CustomerServe, Integer> {

    @Resource
    private CustomerServeMapper customerServeMapper;

    // 查询服务数据列表
    public Map<String, Object> queryCustomerServeByParams(CustomerServeQuery customerServeQuery) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(customerServeQuery.getPage(), customerServeQuery.getLimit());
        PageInfo<CustomerServe> pageInfo = new PageInfo<>(customerServeMapper.selectByParams(customerServeQuery));

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());

        map.put("data", pageInfo.getList());

        return map;
    }
}
