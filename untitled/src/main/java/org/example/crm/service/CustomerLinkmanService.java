package org.example.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.crm.base.BaseService;
import org.example.crm.dao.CustomerLinkmanMapper;
import org.example.crm.query.CustomerLinkmanQuery;
import org.example.crm.vo.CustomerLinkman;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerLinkmanService extends BaseService<CustomerLinkman, Integer> {
    @Resource
    private CustomerLinkmanMapper customerLinkmanMapper;

    public Map<String, Object> queryCustomerLinkmanByParams(CustomerLinkmanQuery customerLinkmanQuery) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(customerLinkmanQuery.getPage(), customerLinkmanQuery.getLimit());
        PageInfo<CustomerLinkman> pageInfo = new PageInfo<>(customerLinkmanMapper.selectByParams(customerLinkmanQuery));

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());

        map.put("data", pageInfo.getList());

        return map;
    }
}
