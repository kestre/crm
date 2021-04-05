package org.example.crm.controller;


import org.example.crm.base.BaseController;
import org.example.crm.base.ResultInfo;
import org.example.crm.query.CustomerLossQuery;
import org.example.crm.query.UserQuery;
import org.example.crm.service.CustomerLossService;
import org.example.crm.vo.CustomerLoss;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("loss")
@Controller
public class CustomerLossController extends BaseController {
    @Resource
    private CustomerLossService customerLossService;

    @RequestMapping("index")
    public String toOrderDetailPage() {

        return "customerLoss/customerLoss";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerLossByParams(CustomerLossQuery customerLossQuery) {
        return customerLossService.queryCustomerLossByParams(customerLossQuery);
    }

    @RequestMapping("toCustomerLossPage")
    public String toCustomerLossPage(Integer lossId, HttpServletRequest request) {

        CustomerLoss customerLoss = customerLossService.selectByPrimaryKey(lossId);

        request.setAttribute("customerLoss", customerLoss);

        return "customerLoss/customerLossData";
    }

    @RequestMapping("updateCustomerLossStateById")
    @ResponseBody
    public ResultInfo updateCustomerLossStateById(Integer id, String lossReason) {
        customerLossService.updateCustomerLossStateById(id, lossReason);

        return success("确认流失成功！");
    }
}
