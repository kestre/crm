package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.service.UserService;
import org.example.crm.utils.LoginUserUtil;
import org.example.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

//    系统登录页
    @RequestMapping("index")
    public String index(){
        return "index";
    }

//    系统界面欢迎页
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

//    后端管理主页面
    @RequestMapping("main")
    public String main(HttpServletRequest request){

        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.selectByPrimaryKey(userId);
        request.getSession().setAttribute("user",user);

        return "main";
    }
}
