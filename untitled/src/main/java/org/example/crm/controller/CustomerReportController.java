package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//RequestMapping("customer")
public class CustomerReportController extends BaseController {

    @RequestMapping("report/{type}")
    public String index(@PathVariable Integer type) {
        if(type != null) {
            if( type == 0 ) {
                //客户贡献分析
                return "report/customerContribution";
            } else if( type == 1 ) {
                //客户构成分析
                return "report/customerMake";
            } else if( type == 2 ) {
                //客户服务分析
                return "report/customerMake";
            } else if( type == 3 ) {
                //客户流失分析
                return "report/customerLoss";
            }
        }
        return "";
    }
}
