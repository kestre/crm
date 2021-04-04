package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.base.ResultInfo;
import org.example.crm.query.CusDevPlanQuery;
import org.example.crm.query.CustomerQuery;
import org.example.crm.service.CustomerService;
import org.example.crm.vo.Customer;
import org.example.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Resource
    private CustomerService customerService;

    @RequestMapping("index")
    public String index(){
        return "customer/customer";
    }

    @RequestMapping("toAddAndUpdatePage")
    public String toAddAndUpdatePage(Integer id, HttpServletRequest request){

        if( id != null) {
            Customer customer = customerService.selectByPrimaryKey(id);

            request.setAttribute("customer", customer);
        }


        return "customer/addAndUpdate";
    }

    @RequestMapping("toCustomerOrderPage")
    public String toCustomerOrderPage(Integer customerId, HttpServletRequest request){

        if( customerId != null) {
            Customer customer = customerService.selectByPrimaryKey(customerId);

            request.setAttribute("customer", customer);
        }

        return "customer/customerOrder";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerByParams(CustomerQuery customerQuery) {

        return customerService.queryCustomerByParams(customerQuery);
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addCustomer(Customer customer) {
        customerService.addCustomer(customer);

        return success("添加成功！");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);

        return success("更新成功！");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteCustomer(Integer[] ids) {

        customerService.deleteCustomer(ids);

        return success("删除成功！");
    }

    @GetMapping("/getLevels")
    @ResponseBody
    public List<String> getLevels() {
        return customerService.getLevels();
    }
}
