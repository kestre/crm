package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.query.CustomerServeQuery;
import org.example.crm.service.CustomerServeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("customer_serve")
@Controller
public class CustomerServeController extends BaseController {

    @Resource
    private CustomerServeService customerServeService;

    @ResponseBody
    @RequestMapping("list")
    public Map<String, Object> queryCustomerServeByParams(CustomerServeQuery customerServeQuery) {
        return customerServeService.queryCustomerServeByParams(customerServeQuery);
    }

    @RequestMapping("index/{type}")
    public String index(@PathVariable Integer type) {
        if (type != null) {
            if (type == 1) { //服务创建
                return "customerServe/customer_serve";
            } else if (type == 2) {  //服务分配

                return "customerServe/customer_serve";
            } else if (type == 3) {  //服务处理

                return "customerServe/customer_serve";
            } else if (type == 4) {  //服务反馈

                return "customerServe/customer_serve";
            } else if (type == 5) {  //服务归档

                return "customerServe/customer_serve";
            } else {
                return "";
            }
        } else {
                return "";
        }
    }
}
