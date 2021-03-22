package org.example.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.example.crm.base.BaseService;
import org.example.crm.dao.SaleChanceMapper;
import org.example.crm.enums.DevResult;
import org.example.crm.enums.StateStatus;
import org.example.crm.query.SaleChanceQuery;
import org.example.crm.utils.AssertUtil;
import org.example.crm.utils.PhoneUtil;
import org.example.crm.vo.SaleChance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaleChanceService extends BaseService<SaleChance, Integer> {

    @Resource
    private SaleChanceMapper saleChanceMapper;

    public Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery){
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(saleChanceQuery.getPage(), saleChanceQuery.getLimit());

        PageInfo<SaleChance> pageInfo = new PageInfo<>(saleChanceMapper.selectByParams(saleChanceQuery));

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());

        map.put("data", pageInfo.getList());

        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addSaleChance(SaleChance saleChance) {
        checkSaleChanceParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());

        saleChance.setIsValid(1);
        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());

        if(StringUtils.isBlank(saleChance.getAssignMan())) {
            saleChance.setState(StateStatus.UNSTATE.getType());
            saleChance.setAssignTime(null);
            saleChance.setDevResult(DevResult.UNDEV.getStatus());
        } else {
            saleChance.setState(StateStatus.STATED.getType());
            saleChance.setAssignTime(new Date());
            saleChance.setDevResult(DevResult.DEVING.getStatus());
        }

        AssertUtil.isTrue(saleChanceMapper.insertSelective(saleChance) != 1, "添加失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSaleChance(SaleChance saleChance) {

        AssertUtil.isTrue(null == saleChance.getId(), "待更新记录不存在！");
        SaleChance temp = saleChanceMapper.selectByPrimaryKey(saleChance.getId());
        AssertUtil.isTrue(null == temp, "待更新记录不存在！");

        checkSaleChanceParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());

        saleChance.setUpdateDate(new Date());

        if(StringUtils.isBlank(temp.getAssignMan())){       //不存在
            if(!StringUtils.isBlank(saleChance.getAssignMan())){        //存在
                saleChance.setState(StateStatus.STATED.getType());
                saleChance.setAssignTime(new Date());
                saleChance.setDevResult(DevResult.DEVING.getStatus());
            }
        }else{
            if(StringUtils.isBlank(saleChance.getAssignMan())){
                saleChance.setState(StateStatus.UNSTATE.getType());
                saleChance.setAssignTime(null);
                saleChance.setDevResult(DevResult.UNDEV.getStatus());
            } else {
                if(!temp.getAssignMan().equals((saleChance.getAssignMan()))){
                    saleChance.setAssignTime(new Date());
                } else {
                    saleChance.setAssignTime(temp.getAssignTime());
                }
            }
        }

        AssertUtil.isTrue(saleChanceMapper.updateByPrimaryKeySelective(saleChance) != 1, "更新失败！");

    }

    // 参数校验
    private void checkSaleChanceParams(String customerName, String linkMan, String linkPhone) {

        AssertUtil.isTrue(StringUtils.isBlank(customerName), "客户名称不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan), "联系人不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone), "联系号码不能为空！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(linkPhone), "联系号码格式不正确！");
    }
}
