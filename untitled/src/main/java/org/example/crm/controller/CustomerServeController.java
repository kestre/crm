package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.base.ResultInfo;
import org.example.crm.query.CustomerServeQuery;
import org.example.crm.service.CustomerServeService;
import org.example.crm.utils.LoginUserUtil;
import org.example.crm.vo.CustomerServe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("customer_serve")
@Controller
public class CustomerServeController extends BaseController {

    @Resource
    private CustomerServeService customerServeService;

    @ResponseBody
    @RequestMapping("list")
    public Map<String, Object> queryCustomerServeByParams(CustomerServeQuery customerServeQuery, Integer flag, HttpServletRequest request) {
        if (flag != null && flag == 1) {
            // 设置条件分配人
            customerServeQuery.setAssigner(LoginUserUtil.releaseUserIdFromCookie(request));
        }

        return customerServeService.queryCustomerServeByParams(customerServeQuery);
    }

    @RequestMapping("index/{type}")
    public String index(@PathVariable Integer type) {
        if (type != null) {
            if (type == 1) { //服务创建
                return "customerServe/customer_serve";
            } else if (type == 2) {  //服务分配

                return "customerServe/customer_serve_assign";
            } else if (type == 3) {  //服务处理

                return "customerServe/customer_serve_proce";
            } else if (type == 4) {  //服务反馈

                return "customerServe/customer_serve_feed_back";
            } else if (type == 5) {  //服务归档

                return "customerServe/customer_serve_archive";
            } else {
                return "";
            }
        } else {
                return "";
        }
    }

    @RequestMapping("toCustomerServeAddPage")
    public String toCustomerServeAddPage(){
        return "customerServe/customerServeAdd";
    }

    @RequestMapping("toCustomerServeAssignPage")
    public String toCustomerServeAssignPage(Integer id, Model model){
        model.addAttribute("customerServe", customerServeService.selectByPrimaryKey(id));

        return "customerServe/customerServeAssignAdd";
    }

    @RequestMapping("toCustomerServeProcePage")
    public String toCustomerServeProcePage(Integer id, Model model){
        model.addAttribute("customerServe", customerServeService.selectByPrimaryKey(id));

        return "customerServe/customerServeProceAdd";
    }

    @RequestMapping("toCustomerServeFeedBackPage")
    public String toCustomerServeFeedBackPage(Integer id, Model model){
        model.addAttribute("customerServe", customerServeService.selectByPrimaryKey(id));

        return "customerServe/customerServeFeedBackAdd";
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addCustomerServe(CustomerServe customerServe) {

        customerServeService.addCustomerServe(customerServe);

        return success("添加成功！");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateCustomerServe(CustomerServe customerServe) {

        customerServeService.updateCustomerServe(customerServe);

        return success("更新成功！");
    }
}
