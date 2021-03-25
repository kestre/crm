package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.service.SaleChanceService;
import org.example.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("cusDevPlan")
public class CusDevPlanController extends BaseController {

    @Resource
    private SaleChanceService saleChanceService;

    @RequestMapping("index")
    public String index(){
        return "cusDevPlan/cusDevPlan";
    }

    @RequestMapping("toCusDevPlan")
    public String toSaleChancePage(Integer id, HttpServletRequest request){

        SaleChance saleChance = saleChanceService.selectByPrimaryKey(id);

        request.setAttribute("saleChance", saleChance);


        return "cusDevPlan/cusDevPlanData";
    }
}
