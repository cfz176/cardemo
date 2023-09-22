package com.cfz.mapper;

import com.cfz.entity.Orders;

import java.util.List;

public interface OrdersTeskMapper {

    /**
     * 查询可接订单
     * @return
     */
    List<Orders> findOrders();
}
