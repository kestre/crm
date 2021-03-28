package org.example.crm.dao;

import org.example.crm.base.BaseMapper;
import org.example.crm.vo.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role, Integer> {
    public List<Map<String, Object>> queryAllRoles(Integer userId);

    public Role selectByRoleName(String roleName);
}