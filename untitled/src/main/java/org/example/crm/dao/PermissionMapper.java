package org.example.crm.dao;

import org.example.crm.base.BaseMapper;
import org.example.crm.vo.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission, Integer> {
    Integer countPermissionByRoleId(Integer roleId);

    void deleteByPermissionByRoleId(Integer roleId);

    List<Integer> queryRoleHasModuleByRoleId(Integer roleId);

    List<String> queryUserHasRoleHasPermissionByUserId(Integer userId);
}