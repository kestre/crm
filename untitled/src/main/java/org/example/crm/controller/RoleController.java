package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.base.ResultInfo;
import org.example.crm.query.RoleQuery;
import org.example.crm.service.RoleService;
import org.example.crm.vo.Role;
import org.example.crm.vo.User;
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
@RequestMapping("role")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;

    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String, Object>> queryAllroles(Integer userId) {
        return roleService.queryAllSales(userId);
    }

    @GetMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(RoleQuery roleQuery) {
        return roleService.queryByParamsForTable(roleQuery);
    }

    @RequestMapping("index")
    public String index() {
        return "role/role";
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addRole(Role role){
        roleService.addRole(role);
        return success("添加成功！");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateRole(Role role){
        roleService.updateRole(role);
        return success("更新成功！");
    }

    @RequestMapping("toAddAndUpdatePage")
    public String toAddAndUpdatePage(Integer id, HttpServletRequest request){

        if( id != null) {
            Role role = roleService.selectByPrimaryKey(id);

            request.setAttribute("role", role);
        }

        return "role/addAndUpdateRole";
    }
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer[] ids) {

        roleService.deleteRole(ids);

        return success("删除成功！");
    }
}
