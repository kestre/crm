package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.base.ResultInfo;
import org.example.crm.exceptions.ParamsException;
import org.example.crm.model.UserModel;
import org.example.crm.service.UserService;
import org.example.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "user",method = RequestMethod.POST)
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName, String userPwd){

        ResultInfo resultInfo = new ResultInfo();


        UserModel userModel = userService.userLogin(userName, userPwd);

        resultInfo.setResult(userModel);

        return resultInfo;
    }

    @PostMapping("updatePwd")
    @ResponseBody
    public ResultInfo updateUserPassword(HttpServletRequest request,
                                         String oldPassword, String newPassword,String repeatPassword){
        ResultInfo resultInfo = new ResultInfo();

        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);

        userService.updatePassWord(userId, oldPassword, newPassword, repeatPassword);


        return resultInfo;
    }

    @GetMapping("toPasswordPage")
    public String toPasswordPage(){

        return "user/password";
    }
}
