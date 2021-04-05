package org.example.crm.service;

import org.example.crm.base.BaseService;
import org.example.crm.dao.CustomerLossMapper;
import org.example.crm.vo.CustomerLoss;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerLossService extends BaseService<CustomerLoss, Integer> {
    @Resource
    private CustomerLossMapper customerLossMapper;
}
