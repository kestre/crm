package org.example.crm.service;

import org.example.crm.base.BaseService;
import org.example.crm.dao.RoleMapper;
import org.example.crm.vo.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RoleService extends BaseService<Role, Integer> {

    @Resource
    private RoleMapper roleMapper;

    public List<Map<String, Object>> queryAllSales() {
        return roleMapper.queryAllRoles();
    }
}
