package org.example.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.crm.base.BaseService;
import org.example.crm.dao.OrderDetailsMapper;
import org.example.crm.query.OrderDetailsQuery;
import org.example.crm.vo.OrderDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderDetailsService extends BaseService<OrderDetails, Integer> {

    @Resource
    private OrderDetailsMapper orderDetailsMapper;

    public Map<String, Object> queryOrderDetailsByParams(OrderDetailsQuery orderDetailsQuery) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(orderDetailsQuery.getPage(), orderDetailsQuery.getLimit());
        PageInfo<OrderDetails> pageInfo = new PageInfo<>(orderDetailsMapper.selectByParams(orderDetailsQuery));

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());

        map.put("data", pageInfo.getList());
        return map;
    }
}
