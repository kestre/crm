package org.example.crm.service;

import org.example.crm.base.BaseService;
import org.example.crm.dao.ModuleMapper;
import org.example.crm.model.TreeModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleService extends BaseService<Module, Integer> {

    @Resource
    private ModuleMapper moduleMapper;

    public List<TreeModel> queryAllModules() {
        return moduleMapper.queryAllModules();
    }
}
