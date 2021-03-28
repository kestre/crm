package org.example.crm.dao;

import org.example.crm.base.BaseMapper;

public interface UserRoleMapper extends BaseMapper {
    Integer countUserRoleByUserId(Integer userId);

    Integer countUserRoleByRoleId(Integer userId);

    Integer deleteUserRoleByUserId(Integer userId);

    Integer deleteUserRoleByRoleId(Integer roleId);
}