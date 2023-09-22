package com.cfz.mapper;

import com.cfz.entity.Orders;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;

import java.util.List;

/**
 * (Orders)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-19 10:24:55
 */
public interface OrdersMapper {

    /**
     * 查询所有订单 条件 分页
     * 根据 phone 联 customer表
     * 外键 mid 工程师表
     * @param orders
     * @return
     */
    List<Orders> findAll(Orders orders);

    /**
     * 添加订单
     * @param orders
     * @return
     */
    Integer insertOrders(Orders orders);

    /**
     * 修改订单
     * @param orders
     * @return
     */
    Integer update(Orders orders);


    List<Orders> selectByStatus(@Param("statusList") String[] statusList
            , @Param("phone") String phone,@Param("contents")String contents);

    /**
     * 根据工程师id查询订单
     */
    List<Orders> listMyOrders(Integer mid);

}

