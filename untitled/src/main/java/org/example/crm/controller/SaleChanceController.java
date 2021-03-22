package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.base.ResultInfo;
import org.example.crm.query.SaleChanceQuery;
import org.example.crm.service.SaleChanceService;
import org.example.crm.utils.CookieUtil;
import org.example.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("saleChance")
public class SaleChanceController extends BaseController {

    @Resource
    private SaleChanceService saleChanceService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery){

        return saleChanceService.querySaleChanceByParams(saleChanceQuery);
    }

    @RequestMapping("index")
    public String index(){
        return "saleChance/saleChance";
    }

    @RequestMapping("addSaleChance")
    @ResponseBody
    public ResultInfo addSaleChance(SaleChance saleChance, HttpServletRequest request){

        String userName = CookieUtil.getCookieValue(request, "userName");

        saleChance.setCreateMan(userName);
        saleChanceService.addSaleChance(saleChance);

        return success("添加成功！");
    }

    @RequestMapping("toSaleChance")
    public String toSaleChancePage(){
        return "saleChance/addAndUpdate";
    }
}
