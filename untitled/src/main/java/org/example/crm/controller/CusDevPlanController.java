package org.example.crm.controller;

import org.example.crm.annoation.RequiredPermission;
import org.example.crm.base.BaseController;
import org.example.crm.base.ResultInfo;
import org.example.crm.query.CusDevPlanQuery;
import org.example.crm.service.CusDevPlanService;
import org.example.crm.service.SaleChanceService;
import org.example.crm.vo.CusDevPlan;
import org.example.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("cusDevPlan")
public class CusDevPlanController extends BaseController {

    @Resource
    private SaleChanceService saleChanceService;

    @Resource
    private CusDevPlanService cusDevPlanService;

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


    @RequiredPermission(code = "102001")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCusDevPlanByParams(CusDevPlanQuery cusDevPlanQuery) {

        return cusDevPlanService.queryCusDevPlanByParams(cusDevPlanQuery);
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addCusDevPlan(CusDevPlan cusDevPlan) {

        cusDevPlanService.addCusDevPlan(cusDevPlan);

        return success("添加成功！");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateCusDevPlan(CusDevPlan cusDevPlan) {

        cusDevPlanService.updateCusDevPlan(cusDevPlan);

        return success("更新成功！");
    }

    @RequestMapping("toAddAndUpdatePage")
    public String toCusDevPlanPage(Integer saleChanceId, Integer id, HttpServletRequest request){
        request.setAttribute("saleChanceId", saleChanceId);

        CusDevPlan cusDevPlan = cusDevPlanService.selectByPrimaryKey(id);
        request.setAttribute("cusDevPlan", cusDevPlan);
        return "cusDevPlan/addAndUpdate";
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer[] ids) {

        cusDevPlanService.deleteCusDevPlan(ids);

        return success("删除成功！");
    }
}
