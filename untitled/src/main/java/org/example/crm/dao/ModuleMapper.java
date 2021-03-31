package org.example.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.example.crm.base.BaseMapper;
import org.example.crm.model.TreeModel;
import org.example.crm.vo.Module;
import org.springframework.ui.Model;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module, Integer> {

    public List<TreeModel> queryAllModules();

    public List<Module> queryModules();

    Module queryModuleByGradeAndModuleName(@Param("grade") Integer grade, @Param("moduleName")String moduleName);

    Module queryModuleByGradeAndUrl(@Param("grade")Integer grade, @Param("url")String url);

    Module queryModuleByOptValue(String optValue);

    Integer queryModulesByParentId(Integer id);
}