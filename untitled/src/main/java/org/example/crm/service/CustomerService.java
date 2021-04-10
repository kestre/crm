package org.example.crm.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.example.crm.base.BaseService;
import org.example.crm.dao.CustomerLossMapper;
import org.example.crm.dao.CustomerMapper;
import org.example.crm.dao.CustomerOrderMapper;
import org.example.crm.query.CustomerQuery;
import org.example.crm.utils.AssertUtil;
import org.example.crm.utils.PhoneUtil;
import org.example.crm.vo.CusDevPlan;
import org.example.crm.vo.Customer;
import org.example.crm.vo.CustomerLoss;
import org.example.crm.vo.CustomerOrder;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CustomerService extends BaseService<Customer, Integer> {
    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private CustomerOrderMapper customerOrderMapper;

    @Resource
    private CustomerLossMapper customerLossMapper;

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

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomer(Integer[] ids){

        AssertUtil.isTrue(null == ids || ids.length < 1, "请选择！");

        AssertUtil.isTrue(customerMapper.deleteBatch(ids) != ids.length, "删除失败！");
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

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerState() {
        List<Customer> lossCustomerList = customerMapper.queryLossCustomers();

        if(lossCustomerList != null && lossCustomerList.size() > 0) {
            List<Integer> lossCustomerIds = new ArrayList<>();

            List<CustomerLoss> customerLossList = new ArrayList<>();

            lossCustomerList.forEach(customer -> {
                CustomerLoss customerLoss = new CustomerLoss();
                customerLoss.setCreateDate(new Date());
                customerLoss.setCusManager(customer.getCusManager());
                customerLoss.setCusName(customer.getName());
                customerLoss.setCusNum(customer.getCusNum());
                customerLoss.setIsValid(1);
                customerLoss.setUpdateDate(new Date());
                customerLoss.setState(0);

                CustomerOrder customerOrder = customerOrderMapper.queryLossCustomerOrderByCustomerId(customer.getId());
                if(customerOrder != null) {
                    customerLoss.setLastOrderTime(customerOrder.getOrderDate());
                }

                customerLossList.add(customerLoss);

                lossCustomerIds.add(customer.getId());
            });

            AssertUtil.isTrue(customerLossMapper.insertBatch(customerLossList) != customerLossList.size(), "客户流失数据转移失败！");

            AssertUtil.isTrue(customerMapper.updateCustomerStateByIds(lossCustomerIds) != lossCustomerIds.size(), "客户流失数据转移失败！");
        }
    }

    //    查询客户贡献
    public Map<String, Object> queryCustomerContributionByParams(CustomerQuery customerQuery) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(customerQuery.getPage(), customerQuery.getLimit());
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(customerMapper.queryCustomerContributionByParams(customerQuery));

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());

        map.put("data", pageInfo.getList());

        return map;
    }

    // 查询客户级别构成
    public Map<String, Object> countCustomerLevelGroup() {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> datalist = customerMapper.countCustomerLevelGroup();
        //折线图x轴
        List<String> dataX = new ArrayList<>();
        //折线图y轴
        List<Integer> dataY = new ArrayList<>();

        if(datalist != null && datalist.size() > 0) {
            datalist.forEach(stringObjectMap -> {
                dataX.add(stringObjectMap.get("level").toString());
                dataY.add(Integer.parseInt(stringObjectMap.get("total").toString()));
            });
        }
        map.put("dataX", dataX);
        map.put("dataY",dataY);

        return map;
    }

    //饼状图
    public Map<String, Object> countCustomerLevelGroup2() {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> datalist = customerMapper.countCustomerLevelGroup();
        //饼状图
        List<String> data1 = new ArrayList<>();
        List<Map<String,Object>> data2 = new ArrayList<>();

        if(datalist != null && datalist.size() > 0) {
            datalist.forEach(stringObjectMap -> {
                data1.add(stringObjectMap.get("level").toString());

                Map<String, Object> map1 = new HashMap<>();
                map1.put("name", stringObjectMap.get("level"));
                map1.put("value", stringObjectMap.get("total"));

                data2.add(map1);
            });
        }

        map.put("data1", data1);
        map.put("data2",data2);

        return map;
    }
}
