package org.example.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.example.crm.base.BaseService;
import org.example.crm.dao.CustomerLossMapper;
import org.example.crm.query.CustomerLossQuery;
import org.example.crm.utils.AssertUtil;
import org.example.crm.vo.CustomerLoss;
import org.example.crm.vo.CustomerOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerLossService extends BaseService<CustomerLoss, Integer> {
    @Resource
    private CustomerLossMapper customerLossMapper;

    public Map<String, Object> queryCustomerLossByParams(CustomerLossQuery customerLossQuery) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(customerLossQuery.getPage(), customerLossQuery.getLimit());
        PageInfo<CustomerLoss> pageInfo = new PageInfo<>(customerLossMapper.selectByParams(customerLossQuery));

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());

        map.put("data", pageInfo.getList());

        return map;
    }

    public void updateCustomerLossStateById(Integer id, String lossReason) {
        AssertUtil.isTrue(null == id, "待确认流失的客户不存在！");

        CustomerLoss customerLoss = customerLossMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null == customerLoss, "待确认流失的客户不存在！");
        AssertUtil.isTrue(StringUtils.isBlank(lossReason), "流失有原因不能为空！");

        customerLoss.setState(1);
        customerLoss.setLossReason(lossReason);
        customerLoss.setConfirmLossTime(new Date());
        customerLoss.setUpdateDate(new Date());

        AssertUtil.isTrue(customerLossMapper.updateByPrimaryKeySelective(customerLoss) < 1, "确认流失客户失败！");
    }
}
