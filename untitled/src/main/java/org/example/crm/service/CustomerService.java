package org.example.crm.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.example.crm.base.BaseService;
import org.example.crm.dao.CustomerMapper;
import org.example.crm.query.CustomerQuery;
import org.example.crm.utils.AssertUtil;
import org.example.crm.utils.PhoneUtil;
import org.example.crm.vo.CusDevPlan;
import org.example.crm.vo.Customer;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService extends BaseService<Customer, Integer> {
    @Resource
    private CustomerMapper customerMapper;

    public Map<String, Object> queryCustomerByParams(CustomerQuery customerQuery){
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(customerQuery.getPage(), customerQuery.getLimit());
        PageInfo<Customer> pageInfo = new PageInfo<>(customerMapper.selectByParams(customerQuery));

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());

        map.put("data", pageInfo.getList());

        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomer(Customer customer) {
        checkCustomerParams(customer.getName(), customer.getCeo(), customer.getPhone());

        Customer temp = customerMapper.queryCustomerByName(customer.getName());
        AssertUtil.isTrue(null != temp, "客户已存在！");

        customer.setIsValid(1);
        customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());

        String cusNum = "Num" + System.currentTimeMillis();
        customer.setCusNum(cusNum);

        // 设置默认流失状态state  state==0：正常用户   state==1：流失客户、
        customer.setState(0);

        AssertUtil.isTrue(customerMapper.insertSelective(customer) < 1, "添加失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomer(Customer customer) {
        AssertUtil.isTrue(null == customer.getId(), "待更新记录不存在！");

        Customer temp = customerMapper.selectByPrimaryKey(customer.getId());
        AssertUtil.isTrue(null == temp, "待更新记录不存在！");

        checkCustomerParams(customer.getName(), customer.getCeo(), customer.getPhone());

        temp = customerMapper.queryCustomerByName(customer.getName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(customer.getId())), "客户已存在！");

        customer.setUpdateDate(new Date());

        AssertUtil.isTrue(customerMapper.updateByPrimaryKeySelective(customer) < 1, "更新失败！");
    }

    public List<String> getLevels() {
        List<String> levels = customerMapper.getLevels();
        AssertUtil.isTrue(levels == null || levels.size() == 0, "获取客户级别失败");
        return levels;
    }

    private void checkCustomerParams(String name, String ceo, String phone) {

        AssertUtil.isTrue(StringUtils.isBlank(name), "客户名称不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(ceo), "法人代表不能为空！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "手机号码格式不正确！");
    }
}
