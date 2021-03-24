package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("cusDevPlan")
public class CusDevPlanController extends BaseController {
    @RequestMapping("index")
    public String index(){
        return "cusDevPlan/cusDevPlan";
    }
}
