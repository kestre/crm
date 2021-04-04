package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.query.OrderDetailsQuery;
import org.example.crm.service.OrderDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("orderDetail")
public class OrderDetailsController  extends BaseController {

    @Resource
    private OrderDetailsService orderDetailsService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryOrderDetailsByParams(OrderDetailsQuery orderDetailsQuery){
        return orderDetailsService.queryOrderDetailsByParams(orderDetailsQuery);
    }
}
