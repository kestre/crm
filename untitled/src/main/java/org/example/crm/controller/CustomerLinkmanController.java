package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.base.ResultInfo;
import org.example.crm.query.CustomerLinkmanQuery;
import org.example.crm.service.CustomerLinkmanService;
import org.example.crm.vo.CustomerLinkman;
import org.example.crm.vo.SaleChance;
import org.example.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addLinkman(CustomerLinkman customerLinkman){
        customerLinkmanService.addLinkman(customerLinkman);
        return success("添加成功！");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateLinkman(CustomerLinkman customerLinkman){
        customerLinkmanService.updateLinkman(customerLinkman);
        return success("更新成功！");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteLinkman(Integer[] ids) {

        customerLinkmanService.deleteLinkman(ids);

        return success("删除成功！");
    }
}
