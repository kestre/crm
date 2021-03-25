package org.example.crm.service;

import org.example.crm.base.BaseService;
import org.example.crm.dao.CusDevPlanMapper;
import org.example.crm.vo.CusDevPlan;

import javax.annotation.Resource;

public class CusDevPlanService extends BaseService<CusDevPlan, Integer> {

    @Resource
    private CusDevPlanMapper cusDevPlanMapper;

    @Resource
    private CusDevPlanService cusDevPlanService;
}
