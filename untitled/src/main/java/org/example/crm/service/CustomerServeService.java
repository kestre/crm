package org.example.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.example.crm.base.BaseService;
import org.example.crm.dao.CustomerMapper;
import org.example.crm.dao.CustomerServeMapper;
import org.example.crm.dao.UserMapper;
import org.example.crm.enums.CustomerServeStatus;
import org.example.crm.query.CustomerServeQuery;
import org.example.crm.utils.AssertUtil;
import org.example.crm.vo.Customer;
import org.example.crm.vo.CustomerServe;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerServeService extends BaseService<CustomerServe, Integer> {

    @Resource
    private CustomerServeMapper customerServeMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private UserMapper userMapper;

    // 查询服务数据列表
    public Map<String, Object> queryCustomerServeByParams(CustomerServeQuery customerServeQuery) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(customerServeQuery.getPage(), customerServeQuery.getLimit());
        PageInfo<CustomerServe> pageInfo = new PageInfo<>(customerServeMapper.selectByParams(customerServeQuery));

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());

        map.put("data", pageInfo.getList());

        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomerServe(CustomerServe customerServe) {

        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getCustomer()), "客户名不能为空！");
        AssertUtil.isTrue(customerMapper.queryCustomerByName(customerServe.getCustomer()) == null, "客户不存在！");

        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServeType()), "请选择服务类型！");
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceRequest()), "服务请求内容不能为空！");

        customerServe.setState(CustomerServeStatus.CREATE.getState());
        customerServe.setIsValid(1);
        customerServe.setCreateDate(new Date());
        customerServe.setUpdateDate(new Date());

        AssertUtil.isTrue(customerServeMapper.insertSelective(customerServe) < 1, "添加失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerServe(CustomerServe customerServe) {
        AssertUtil.isTrue(customerServe.getId() == null
                || customerServeMapper.selectByPrimaryKey(customerServe.getId()) == null, "待更新记录不存在！");

        if(CustomerServeStatus.ASSIGNED.getState().equals(customerServe.getState())) {
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getAssigner()), "待分配用户不存在");
            AssertUtil.isTrue(userMapper.selectByPrimaryKey(Integer.parseInt(customerServe.getAssigner())) == null, "待分配用户不存在！");
            customerServe.setAssignTime(new Date());

        } else if(CustomerServeStatus.PROCED.getState().equals(customerServe.getState())) {
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceProce()), "服务处理内容不能为空！");

            customerServe.setServiceProceTime(new Date());

        } else if(CustomerServeStatus.FEED_BACK.getState().equals(customerServe.getState())) {
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceProceResult()), "服务反馈内容不能为空！");
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getSatisfied()), "请选择服务反馈满意度！");

            customerServe.setState(CustomerServeStatus.ARCHIVED.getState());
        }
        customerServe.setUpdateDate(new Date());

        AssertUtil.isTrue(customerServeMapper.updateByPrimaryKeySelective(customerServe) < 1, "更新失败！");
    }
}
