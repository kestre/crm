package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.query.CustomerLinkmanQuery;
import org.example.crm.service.CustomerLinkmanService;
import org.example.crm.vo.CustomerLinkman;
import org.example.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("linkman")
@Controller
public class CustomerLinkmanController extends BaseController {
    @Resource
    private CustomerLinkmanService customerLinkmanService;

    @RequestMapping("index")
    public String toLinkmanPage() {

        return "linkman/customerLinkman";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerLinkmanByParams(CustomerLinkmanQuery customerLinkmanQuery) {
        return customerLinkmanService.queryCustomerLinkmanByParams(customerLinkmanQuery);
    }

    @RequestMapping("toAddAndUpdatePage")
    public String toaddAndUpdatePage(Integer id, HttpServletRequest request){
        if( id != null) {
            CustomerLinkman customerLinkman = customerLinkmanService.selectByPrimaryKey(id);

            request.setAttribute("customerLinkman", customerLinkman);
        }

        return "linkman/addAndUpdate";
    }
}
