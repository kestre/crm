package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.dao.CustomerMapper;
import org.example.crm.service.*;
import org.example.crm.utils.LoginUserUtil;
import org.example.crm.vo.CustomerOrder;
import org.example.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private CustomerService customerService;

    @Resource
    private CustomerContactService customerContactService;

    @Resource
    private CustomerOrderService customerOrderService;



//    系统登录页
    @RequestMapping("index")
    public String index(){
        return "index";
    }

//    系统界面欢迎页
    @RequestMapping("welcome")
    public String welcome(Model model){

        model.addAttribute("newCusCount", customerService.queryNewCustomerCount());
        model.addAttribute("recentContactTime", customerContactService.queryRecentContactTime());
        model.addAttribute("cooperateCus", customerService.queryCooperateCus());
        model.addAttribute("newOrderCount", customerOrderService.queryNewCustomerOrderCount());

        return "welcome";
    }

//    后端管理主页面
    @RequestMapping("main")
    public String main(HttpServletRequest request){

        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.selectByPrimaryKey(userId);
        request.getSession().setAttribute("user",user);

        // 通过登录用户ID查询当前用户拥有的资源列表（查询对应资源的授权码）
        List<String> permissions = permissionService.queryUserHasRoleHasPermissionByUserId(userId);


        request.getSession().setAttribute("permissions", permissions);

        return "main";
    }
}
