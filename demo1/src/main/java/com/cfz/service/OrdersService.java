package com.cfz.service;

import com.cfz.entity.Orders;
import com.cfz.entity.dto.OrdersDto;
import com.cfz.entity.vo.OrdersVo;
import com.cfz.entity.vo.PageVo;

import java.util.List;

/**
 * (Orders)表服务接口
 *
 * @author makejava
 * @since 2023-07-19 10:24:56
 */
public interface OrdersService {

    /**
     * 询所有订单接口
     * @param ordersDto
     * @param page
     * @param limit
     * @return
     */
    PageVo findAll(OrdersDto ordersDto, Integer page, Integer limit);

    /**
     * 添加订单
     * @param orders
     * @return
     */
    String insertOrders(OrdersDto orders);

    /**
     * 修改订单
     * @param ordersDto
     * @return
     */
    Integer update(OrdersDto ordersDto);

    /**
     * 查找未完成订单
     * @param phone
     * @param contents
     * @return
     */
    List<Orders>  selectByStatus(String phone,String contents);

    /**
     * 根据工程师id查询订单
     */
    List<OrdersVo> listMyOrders(Integer mid);
}
