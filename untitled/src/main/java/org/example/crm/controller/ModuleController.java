package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.model.TreeModel;
import org.example.crm.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {
    @Resource
    private ModuleService moduleService;

    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<TreeModel> queryAllModules(Integer roleId) {
        return moduleService.queryAllModules(roleId);
    }

    @RequestMapping("toModulePage")
    public String toModulePage(Integer roleId, HttpServletRequest request) {
        request.setAttribute("roleId", roleId);

        return "role/grant";
    }
}
