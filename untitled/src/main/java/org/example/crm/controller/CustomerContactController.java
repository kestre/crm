package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.base.ResultInfo;
import org.example.crm.query.CustomerContactQuery;
import org.example.crm.query.CustomerLinkmanQuery;
import org.example.crm.service.CustomerContactService;
import org.example.crm.vo.CustomerContact;
import org.example.crm.vo.CustomerLinkman;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("contact")
public class CustomerContactController extends BaseController {
    @Resource
    private CustomerContactService customerContactService;

    @RequestMapping("index")
    public String toContactPage() {

        return "contact/customerContact";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerContactByParams(CustomerContactQuery customerContactQuery) {
        return customerContactService.queryCustomerContactByParams(customerContactQuery);
    }

    @RequestMapping("toAddAndUpdatePage")
    public String toAddAndUpdatePage(Integer id, HttpServletRequest request){
        if( id != null) {
            CustomerContact customerContact = customerContactService.selectByPrimaryKey(id);

            request.setAttribute("customerContact", customerContact);
        }

        return "contact/addAndUpdate";
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addContact(CustomerContact customerContact){
        customerContactService.addContact(customerContact);
        return success("添加成功！");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateContact(CustomerContact customerContact){
        customerContactService.updateContact(customerContact);
        return success("更新成功！");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteContact(Integer[] ids) {

        customerContactService.deleteContact(ids);

        return success("删除成功！");
    }
}
