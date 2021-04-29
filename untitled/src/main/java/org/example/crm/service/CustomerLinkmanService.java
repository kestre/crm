package org.example.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.example.crm.base.BaseService;
import org.example.crm.dao.CustomerLinkmanMapper;
import org.example.crm.dao.CustomerMapper;
import org.example.crm.query.CustomerLinkmanQuery;
import org.example.crm.utils.AssertUtil;
import org.example.crm.utils.PhoneUtil;
import org.example.crm.vo.Customer;
import org.example.crm.vo.CustomerLinkman;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerLinkmanService extends BaseService<CustomerLinkman, Integer> {
    @Resource
    private CustomerLinkmanMapper customerLinkmanMapper;

    @Resource
    private CustomerMapper customerMapper;

    public Map<String, Object> queryCustomerLinkmanByParams(CustomerLinkmanQuery customerLinkmanQuery) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(customerLinkmanQuery.getPage(), customerLinkmanQuery.getLimit());
        PageInfo<CustomerLinkman> pageInfo = new PageInfo<>(customerLinkmanMapper.selectByParams(customerLinkmanQuery));

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());

        map.put("data", pageInfo.getList());

        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteLinkman(Integer[] ids){

        AssertUtil.isTrue(null == ids || ids.length < 1, "请选择！");

        AssertUtil.isTrue(customerLinkmanMapper.deleteBatch(ids) != ids.length, "删除失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addLinkman(CustomerLinkman customerLinkman) {
        System.out.println(customerLinkman.getLinkName());
        checkLinkmanParams(customerLinkman.getLinkName(), customerLinkman.getGender(), customerLinkman.getPhone(), customerLinkman.getCusId());

        customerLinkman.setIsValid(1);
        customerLinkman.setCreateDate(new Date());
        customerLinkman.setUpdateDate(new Date());
        AssertUtil.isTrue(customerLinkmanMapper.insertSelective(customerLinkman) < 1, "添加失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateLinkman(CustomerLinkman customerLinkman) {
        AssertUtil.isTrue(null == customerLinkman.getId(), "待更新记录不存在！");

        CustomerLinkman temp = customerLinkmanMapper.selectByPrimaryKey(customerLinkman.getId());
        AssertUtil.isTrue(null == temp, "待更新记录不存在！");

        checkLinkmanParams(customerLinkman.getLinkName(), customerLinkman.getGender(), customerLinkman.getPhone(), customerLinkman.getCusId());

        customerLinkman.setUpdateDate(new Date());

        AssertUtil.isTrue(customerLinkmanMapper.updateByPrimaryKeySelective(customerLinkman) < 1, "更新失败！");
    }

    private void checkLinkmanParams(String name, String gender, String phone, Integer cusId) {

        AssertUtil.isTrue(StringUtils.isBlank(name), "联系人名称不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(gender), "联系人性别不能为空！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "手机号码格式不正确！");
        Customer temp = customerMapper.selectByPrimaryKey(cusId);
        AssertUtil.isTrue( null == temp, "所属客户不存在！");
    }
}
