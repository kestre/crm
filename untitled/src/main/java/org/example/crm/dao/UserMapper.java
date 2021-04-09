package org.example.crm.dao;

import org.example.crm.base.BaseMapper;
import org.example.crm.vo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User, Integer> {

    public User queryUserByName(String userName);

    List<Map<String, Object>> queryAllSales();

    List<Map<String, Object>> queryAllCustomerManagers();
}