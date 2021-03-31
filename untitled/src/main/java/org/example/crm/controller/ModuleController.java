package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.base.ResultInfo;
import org.example.crm.model.TreeModel;
import org.example.crm.service.ModuleService;
import org.example.crm.utils.AssertUtil;
import org.example.crm.vo.Module;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    @GetMapping("list")
    @ResponseBody
    public Map<String, Object> queryModules() {
        return moduleService.queryModels();
    }

    @RequestMapping("index")
    public String index() {
        return "module/module";
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addModule(Module module) {
        moduleService.addModule(module);

        return success("添加成功！");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateModule(Module module) {
        moduleService.updateModule(module);

        return success("修改成功！");
    }


    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteModule(Integer id) {
        moduleService.deleteModule(id);

        return success("删除成功！");
    }

    @RequestMapping("toAddModule")
    public String toAddModule(Integer grade, Integer parentId, HttpServletRequest request) {
        // 参数校验
        AssertUtil.isTrue(!(grade == 0 || grade == 1 || grade == 2), "级别不符合规范");
        AssertUtil.isTrue(parentId == null, "父级id不能为空");
        if (grade == 0) {
            AssertUtil.isTrue(parentId != -1, "父级id错误");
        } else {
            AssertUtil.isTrue(moduleService.selectByPrimaryKey(parentId) == null, "父级id不存在");
        }

        request.setAttribute("grade", grade);
        request.setAttribute("parentId", parentId);

        return "module/addModule";
    }

    @RequestMapping("toUpdateModule")
    public String toUpdateModule(Integer id, HttpServletRequest request) {

        Module module = moduleService.selectByPrimaryKey(id);

        request.setAttribute("module", module);

        return "module/updateModule";
    }
}
