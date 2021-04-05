package org.example.crm.controller;


import org.example.crm.base.BaseController;
import org.example.crm.service.CustomerLossService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("Loss")
@Controller
public class CustomerLossController extends BaseController {
    @Resource
    private CustomerLossService customerLossService;

    @RequestMapping("index")
    public String toOrderDetailPage(Integer orderId, HttpServletRequest request) {

        return "customerLoss/customerLoss";
    }
}
