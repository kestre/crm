package org.example.crm.service;

import org.example.crm.base.BaseService;
import org.example.crm.dao.ModuleMapper;
import org.example.crm.dao.PermissionMapper;
import org.example.crm.model.TreeModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
