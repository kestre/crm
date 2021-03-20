package org.example.crm.dao;

import org.example.crm.base.BaseMapper;
import org.example.crm.vo.User;

public interface UserMapper extends BaseMapper<User, Integer> {

    public User queryUserByName(String userName);
}