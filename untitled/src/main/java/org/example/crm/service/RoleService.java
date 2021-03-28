package org.example.crm.service;

import org.apache.commons.lang3.StringUtils;
import org.example.crm.base.BaseService;
import org.example.crm.dao.RoleMapper;
import org.example.crm.dao.UserRoleMapper;
import org.example.crm.utils.AssertUtil;
import org.example.crm.vo.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleService extends BaseService<Role, Integer> {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    public List<Map<String, Object>> queryAllSales(Integer userId) {
        return roleMapper.queryAllRoles(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole (Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名称不能为空！");

        Role temp = roleMapper.selectByRoleName(role.getRoleName());
        AssertUtil.isTrue(temp != null, "用户名已被占用！");

        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());

        AssertUtil.isTrue(roleMapper.insertSelective(role) < 1, "添加失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole (Role role) {

        AssertUtil.isTrue(null == role.getId(), "待更新记录不存在！");

        Role temp = roleMapper.selectByPrimaryKey(role.getId());
        AssertUtil.isTrue(null == temp, "待更新记录不存在！");

        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名称不能为空！");
        temp = roleMapper.selectByRoleName(role.getRoleName());
        AssertUtil.isTrue(temp != null && !(temp.getId().equals(role.getId())), "用户名已被占用！");

        role.setUpdateDate(new Date());

        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role) < 1, "更新失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Integer[] ids){

        AssertUtil.isTrue(null == ids || ids.length < 1, "请选择！");

        AssertUtil.isTrue(roleMapper.deleteBatch(ids) != ids.length, "删除失败！");

        for (Integer roleId: ids) {
            Integer count = userRoleMapper.countUserRoleByRoleId(roleId);

            if (count > 0) {
                AssertUtil.isTrue(userRoleMapper.deleteUserRoleByRoleId(roleId) != count, "删除失败！");
            }
        }
    }
}
