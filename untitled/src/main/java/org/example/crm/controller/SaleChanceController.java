package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.base.ResultInfo;
import org.example.crm.enums.StateStatus;
import org.example.crm.query.SaleChanceQuery;
import org.example.crm.service.SaleChanceService;
import org.example.crm.utils.CookieUtil;
import org.example.crm.utils.LoginUserUtil;
import org.example.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("saleChance")
public class SaleChanceController extends BaseController {

    @Resource
    private SaleChanceService saleChanceService;

    //      flag = 1 查询客户开发计划
    //      flag = null 查询营销机会数据
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery, Integer flag,
                                                       HttpServletRequest request) {

        if(flag != null && flag == 1){
            saleChanceQuery.setState(StateStatus.STATED.getType());
            Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
            saleChanceQuery.setAssignMan(userId);
        }
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

    @RequestMapping("updateSaleChance")
    @ResponseBody
    public ResultInfo updateSaleChance(SaleChance saleChance){

        saleChanceService.updateSaleChance(saleChance);
        return success("更新成功！");
    }

    @RequestMapping("toSaleChance")
    public String toSaleChancePage(Integer id, HttpServletRequest request){
        if( id != null) {
            SaleChance saleChance = saleChanceService.selectByPrimaryKey(id);

            request.setAttribute("saleChance", saleChance);
        }

        return "saleChance/addAndUpdate";
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteSaleChance(Integer[] ids) {
        saleChanceService.deleteSaleChance(ids);

        return success("删除成功！");
    }

    @PostMapping("updateDevResult")
    @ResponseBody
    public ResultInfo updateDevResult(Integer id, Integer devResult) {

        saleChanceService.updateSaleChanceDevResult(id, devResult);

        return success("更新成功！");
    }
}
