package org.example.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.example.crm.base.BaseService;
import org.example.crm.dao.CustomerContactMapper;
import org.example.crm.dao.CustomerMapper;
import org.example.crm.query.CustomerContactQuery;
import org.example.crm.utils.AssertUtil;
import org.example.crm.utils.PhoneUtil;
import org.example.crm.vo.Customer;
import org.example.crm.vo.CustomerContact;
import org.example.crm.vo.CustomerLinkman;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerContactService extends BaseService<CustomerContact, Integer> {
    @Resource
    private CustomerContactMapper customerContactMapper;

    @Resource
    private CustomerMapper customerMapper;

    // 获得近一个月联系客人次数
    public int queryRecentContactTime(){

        return customerContactMapper.queryRecentContactTime();
    }

    public Map<String, Object> queryCustomerContactByParams(CustomerContactQuery customerContactQuery) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(customerContactQuery.getPage(), customerContactQuery.getLimit());
        PageInfo<CustomerContact> pageInfo = new PageInfo<>(customerContactMapper.selectByParams(customerContactQuery));

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());

        map.put("data", pageInfo.getList());

        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addContact(CustomerContact customerContact) {

        checkContactParams(customerContact.getCusId(), customerContact.getContactTime(), customerContact.getAddress());

        customerContact.setIsValid(1);
        customerContact.setCreateDate(new Date());
        customerContact.setUpdateDate(new Date());

        AssertUtil.isTrue(customerContactMapper.insertSelective(customerContact) < 1, "添加失败！");

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateContact(CustomerContact customerContact) {
        checkContactParams(customerContact.getCusId(), customerContact.getContactTime(), customerContact.getAddress());
        customerContact.setUpdateDate(new Date());

        AssertUtil.isTrue(customerContactMapper.updateByPrimaryKeySelective(customerContact) < 1, "更新失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteContact(Integer[] ids) {

        AssertUtil.isTrue(null == ids || ids.length < 1, "请选择！");

        AssertUtil.isTrue(customerContactMapper.deleteBatch(ids) != ids.length, "删除失败！");
    }

    private void checkContactParams(Integer cusId, Date contactTime, String address) {
        Customer temp = customerMapper.selectByPrimaryKey(cusId);
        AssertUtil.isTrue( null == temp, "所属客户不存在！");
        AssertUtil.isTrue(contactTime == null, "日期不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(address), "地址不能为空！");
    }
}
