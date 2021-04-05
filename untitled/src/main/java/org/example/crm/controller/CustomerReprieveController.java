package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.base.ResultInfo;
import org.example.crm.query.CustomerLossQuery;
import org.example.crm.query.CustomerOrderQuery;
import org.example.crm.query.CustomerReprieveQuery;
import org.example.crm.service.CustomerReprieveService;
import org.example.crm.service.CustomerService;
import org.example.crm.utils.CookieUtil;
import org.example.crm.vo.CustomerReprieve;
import org.example.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("customerRep")
@Controller
public class CustomerReprieveController extends BaseController {
    @Resource
    private CustomerReprieveService customerReprieveService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerReprieveByParams(CustomerReprieveQuery customerReprieveQuery) {

        return customerReprieveService.queryCustomerReprieveByParams(customerReprieveQuery);
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addCustomerRepr(CustomerReprieve customerReprieve){
        customerReprieveService.addCustomerRepr(customerReprieve);

        return success("添加成功！");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCustomerRepr(CustomerReprieve customerReprieve){
        customerReprieveService.updateCustomerRepr(customerReprieve);

        return success("修改成功！");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCustomerRepr(Integer[] ids) {
        customerReprieveService.deleteCustomerRepr(ids);

        return success("删除成功！");
    }
}

