package org.example.crm.dao;

import org.example.crm.base.BaseMapper;

public interface UserRoleMapper extends BaseMapper {
    Integer countUserRoleById(Integer userId);

    Integer deleteUserRoleByUserId(Integer userId);
}