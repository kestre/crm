package org.example.crm.dao;

import org.example.crm.base.BaseMapper;
import org.example.crm.vo.Permission;

public interface PermissionMapper extends BaseMapper<Permission, Integer> {
    Integer countPermissionByRoleId(Integer roleId);

    void deleteByPermissionByRoleId(Integer roleId);
}