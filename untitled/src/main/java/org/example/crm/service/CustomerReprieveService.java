package org.example.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.example.crm.base.BaseService;
import org.example.crm.dao.CustomerLossMapper;
import org.example.crm.dao.CustomerReprieveMapper;
import org.example.crm.query.CustomerLossQuery;
import org.example.crm.query.CustomerReprieveQuery;
import org.example.crm.utils.AssertUtil;
import org.example.crm.vo.CustomerLoss;
import org.example.crm.vo.CustomerReprieve;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerReprieveService extends BaseService<CustomerReprieve,Integer> {

    @Resource
    private CustomerReprieveMapper customerReprieveMapper;

    @Resource
    private CustomerLossMapper customerLossMapper;

    public Map<String, Object> queryCustomerReprieveByParams(CustomerReprieveQuery customerReprieveQuery) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(customerReprieveQuery.getPage(), customerReprieveQuery.getLimit());
        PageInfo<CustomerReprieve> pageInfo = new PageInfo<>(customerReprieveMapper.selectByParams(customerReprieveQuery));

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());

        map.put("data", pageInfo.getList());
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomerRepr(CustomerReprieve customerReprieve) {
        AssertUtil.isTrue(null == customerReprieve.getLossId()
                || customerLossMapper.selectByPrimaryKey(customerReprieve.getLossId()) == null, "流失客户记录不存在！");

        AssertUtil.isTrue(StringUtils.isBlank(customerReprieve.getMeasure()), "暂缓措施内容不能为空！");

        customerReprieve.setIsValid(1);
        customerReprieve.setCreateDate(new Date());
        customerReprieve.setUpdateDate(new Date());

        AssertUtil.isTrue(customerReprieveMapper.insertSelective(customerReprieve) < 1, "添加暂缓数据失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerRepr(CustomerReprieve customerReprieve) {
        AssertUtil.isTrue(null == customerReprieve.getId()
                || customerReprieveMapper.selectByPrimaryKey(customerReprieve.getId()) == null, "待更新记录不存在！");

        AssertUtil.isTrue(null == customerReprieve.getLossId()
                || customerLossMapper.selectByPrimaryKey(customerReprieve.getLossId()) == null, "流失客户记录不存在！");

        AssertUtil.isTrue(StringUtils.isBlank(customerReprieve.getMeasure()), "暂缓措施内容不能为空！");

        customerReprieve.setUpdateDate(new Date());

        AssertUtil.isTrue(customerReprieveMapper.updateByPrimaryKeySelective(customerReprieve) < 1, "更新失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomerRepr(Integer[] ids) {
        AssertUtil.isTrue(ids == null || ids.length < 1, "请选择！");
        AssertUtil.isTrue(customerReprieveMapper.deleteBatch(ids) != ids.length, "删除失败！");
    }
}
