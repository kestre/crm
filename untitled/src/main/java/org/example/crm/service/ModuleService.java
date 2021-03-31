package org.example.crm.service;

import org.apache.commons.lang3.StringUtils;
import org.example.crm.base.BaseService;
import org.example.crm.dao.ModuleMapper;
import org.example.crm.dao.PermissionMapper;
import org.example.crm.model.TreeModel;
import org.example.crm.utils.AssertUtil;
import org.example.crm.vo.Module;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleService extends BaseService<Module, Integer> {

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    public List<TreeModel> queryAllModules(Integer roleId) {
        List<TreeModel> treeModelList = moduleMapper.queryAllModules();

        List<Integer> permissionIds = permissionMapper.queryRoleHasModuleByRoleId(roleId);

        if (permissionIds != null && permissionIds.size() > 0) {
            treeModelList.forEach(treeModel -> {
                    if(permissionIds.contains(treeModel.getId())){
                        treeModel.setChecked(true);
                }
            });
        }

        return treeModelList;
    }

    public Map<String, Object> queryModels() {
        Map<String, Object> map = new HashMap<>();

        List<Module> moduleList = moduleMapper.queryModules();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", moduleList.size());
        map.put("data", moduleList);

        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addModule(Module module) {
        // grade=1 url非空
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade ==1 ||grade == 2), "菜单层级不合法！");
        // moduleName 唯一非空
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "模块名字不能为空！");
        AssertUtil.isTrue(null != moduleMapper.queryModuleByGradeAndModuleName(grade, module.getModuleName()), "该名称已存在！");
        // url grade = 1 非空唯一
        if (grade == 1) {
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()), "URL不能为空");
            AssertUtil.isTrue(null != moduleMapper.queryModuleByGradeAndUrl(grade, module.getUrl()), "该URL已存在！");
        }
        /*
        * parentId
        * grade = 0 -1
        * grade =1||2 非空且存在
        * */
        Module parent = moduleMapper.selectByPrimaryKey(module.getParentId());

        if (grade == 0) {
            module.setParentId(-1);
        } else {
            AssertUtil.isTrue(null == module.getParentId(), "父级菜单不能为空！");
            AssertUtil.isTrue(null == parent, "父级菜单不存在！");
            // 设置parent_opt_value
            module.setParentOptValue(parent.getOptValue());
        }
        // optValue 非空
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "权限码不能为空！");
        AssertUtil.isTrue(null != moduleMapper.queryModuleByOptValue(module.getOptValue()), "权限码已存在！");
        /*
        * isValid = 1
        * createDate = now
        * updataDate = now
        * */
        module.setIsValid(1);
        module.setCreateDate(new Date());
        module.setUpdateDate(new Date());

        AssertUtil.isTrue(moduleMapper.insertSelective(module) < 1, "添加失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateModule(Module module) {
        // id非空
        AssertUtil.isTrue(null == module.getId(), "待更新记录不存在！");

        Module temp = moduleMapper.selectByPrimaryKey(module.getId());
        AssertUtil.isTrue(null == temp, "待更新记录不存在！");
        // grade=1 url非空
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade ==1 ||grade == 2), "菜单层级不合法！");
        // moduleName 唯一非空
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "模块名字不能为空！");

        temp = moduleMapper.queryModuleByGradeAndModuleName(grade, module.getModuleName());
        if (temp != null) {
            AssertUtil.isTrue(!(temp.getId().equals(module.getId())),"该名称已存在！");
        }
        // url grade = 1 非空唯一
        if (grade == 1) {
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()), "URL不能为空");

            temp = moduleMapper.queryModuleByGradeAndUrl(grade, module.getUrl());
            if( temp != null) {
                AssertUtil.isTrue(!(temp.getId().equals(module.getId())), "该URL已存在！");
            }
        }
        /*
         * parentId
         * grade = 0 -1
         * grade =1||2 非空且存在
         * */
        temp = moduleMapper.selectByPrimaryKey(module.getParentId());

        if (grade == 0) {
            module.setParentId(-1);
        } else {
            AssertUtil.isTrue(null == module.getParentId(), "父级菜单不能为空！");
            AssertUtil.isTrue(null == temp, "父级菜单不存在！");
            // 设置parent_opt_value
            module.setParentOptValue(temp.getOptValue());
        }
        // optValue 非空
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "权限码不能为空！");
        temp = moduleMapper.queryModuleByOptValue(module.getOptValue());

        if(temp != null){
            AssertUtil.isTrue(!(temp.getId().equals(module.getId())), "权限码已存在！");
        }

        module.setUpdateDate(new Date());

        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(module) < 1, "修改失败！");
    }

    public void deleteModule(Integer id) {

        AssertUtil.isTrue(null == id, "待更新记录不存在！");

        Module temp = moduleMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue( null == temp, "待更新记录不存在！");

        Integer count = moduleMapper.queryModulesByParentId(id);

        AssertUtil.isTrue(count > 0, "存在子目录！");

        count = permissionMapper.countPermissionByModuleId(id);

        if (count > 0) {
            permissionMapper.deleteByPermissionByModuleId(id);
        }

        temp.setIsValid(0);
        temp.setUpdateDate(new Date());
        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(temp) < 1, "删除失败！");
    }
}
