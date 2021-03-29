package org.example.crm.dao;

import org.example.crm.base.BaseMapper;
import org.example.crm.model.TreeModel;
import org.example.crm.vo.Module;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module, Integer> {

    public List<TreeModel> queryAllModules();
}