package org.example.crm.service;

import org.example.crm.base.BaseService;
import org.example.crm.dao.UserMapper;
import org.example.crm.vo.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRoleService extends BaseService<UserRole, Integer> {

    @Resource
    private UserMapper userMapper;
}
