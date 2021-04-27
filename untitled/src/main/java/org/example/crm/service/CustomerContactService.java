package org.example.crm.service;

import org.example.crm.base.BaseService;
import org.example.crm.dao.CustomerContactMapper;
import org.example.crm.vo.CustomerContact;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerContactService extends BaseService<CustomerContact, Integer> {
    @Resource
    private CustomerContactMapper customerContactMapper;

    // 获得近一个月联系客人次数
    public int queryRecentContactTime(){

        return customerContactMapper.queryRecentContactTime();
    }
}
