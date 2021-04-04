package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.query.CustomerOrderQuery;
import org.example.crm.service.CustomerOrderService;
import org.example.crm.vo.Customer;
import org.example.crm.vo.CustomerOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("order")
@Controller
public class CustomerOrderController extends BaseController {
    @Resource
    private CustomerOrderService customerOrderService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerOrderByParams(CustomerOrderQuery customerOrderQuery) {

        return customerOrderService.queryCustomerOrderByParams(customerOrderQuery);
    }

    @RequestMapping("toOrderDetailPage")
    public String toOrderDetailPage(Integer orderId, HttpServletRequest request) {
        if(orderId != null) {
            Map<String, Object> map = customerOrderService.queryOrderById(orderId);

            request.setAttribute("order", map);
        }
        return "customer/orderDetail";
    }
}

