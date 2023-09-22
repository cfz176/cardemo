package com.cfz.service.impl;

import com.cfz.constants.SystemConstants;
import com.cfz.entity.Customer;
import com.cfz.entity.Orders;
import com.cfz.entity.dto.OrdersDto;
import com.cfz.entity.vo.OrdersVo;
import com.cfz.entity.vo.PageVo;
import com.cfz.mapper.CustomerMapper;
import com.cfz.mapper.OrdersMapper;
import com.cfz.service.OrdersService;
import com.cfz.utils.BeanCopyUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Orders)表服务实现类
 *
 * @author makejava
 * @since 2023-07-19 10:24:56
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    CustomerMapper customerMapper;

    /**
     * 查询所有订单
     * @param ordersDto
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageVo findAll(OrdersDto ordersDto, Integer page, Integer limit) {
        //dto转换
        Orders orders = BeanCopyUtils.copyBean(ordersDto, Orders.class);
        //设置分页
        PageHelper.startPage(page, limit);
        //查询订单
        Page<Orders> ordersPage = (Page<Orders>) ordersMapper.findAll(orders);
        //加入子表信息
        ordersPage.getResult().stream()
                .map(orders1 -> {
                    Customer customer = orders1.getCustomer();
                    orders1.setName(customer.getName());
                    orders1.setCar(customer.getCar());
                    return orders1;
                }).collect(Collectors.toList());
        //转换vo
        List<OrdersVo> ordersVos = BeanCopyUtils.copyBeanList(ordersPage.getResult(), OrdersVo.class);
        return new PageVo(ordersPage.getTotal(),ordersVos);
    }

    /**
     * 添加订单 后台用户
     * @param ordersDto
     * @return
     */
    @Override
    @Transactional
    public String insertOrders(OrdersDto ordersDto) {
        //dto转换
        Orders orders = BeanCopyUtils.copyBean(ordersDto, Orders.class);
//        //判断用户是否有未完成订单
//        String[] StatusArr = {SystemConstants.ORDERS_STATUS_FINISH
//                ,SystemConstants.ORDERS_STATUS_EVALUATED};
//        List<Orders> ordersList = ordersMapper.selectByStatus(StatusArr,orders.getPhone(),orders.getContents());
//        if (!ObjectUtils.isEmpty(ordersList)) {
//            return "您有未完成的订单";
//        }
        //添加是时间
        Date date = new Date();
        orders.setCreatetime(date);
        orders.setUpdatetime(date);
        //默认为刚下单
        orders.setStatus(SystemConstants.MASTER_STATUS_DEFAULT);
        Integer integer = ordersMapper.insertOrders(orders);

        //判断客户是否存在
        Customer customer = new Customer();
        customer.setPhone(ordersDto.getPhone());
        //根据手机号查询
        customerMapper.selectList(customer);
        //不存在
        if (ObjectUtils.isEmpty(customer)) {
            //添加客户
            customer.setName(ordersDto.getName());
            customer.setCar(ordersDto.getCar());
            customer.setCreatetime(date);
            customer.setScore("0");
            customer.setPhone(ordersDto.getPhone());
            customer.setDel(SystemConstants.DEL_DEFAULT);
            return customerMapper.add(customer)+"";
        }
        //存在
        return integer+"";
    }

    /**
     * 查找未完成订单
     * @param phone
     * @param contents
     * @return
     */
    public List<Orders> selectByStatus(String phone,String contents){
        String[] StatusArr = {SystemConstants.ORDERS_STATUS_RECEIVED
                ,SystemConstants.ORDERS_STATUS_DEFAULT};
        List<Orders> ordersList = ordersMapper.selectByStatus(StatusArr,phone,contents);
        if (!ObjectUtils.isEmpty(ordersList)) {
            return ordersList;
        }
        return null;
    }

    /**
     * 修改订单
     * @param ordersDto
     * @return
     */
    @Override
    public Integer update(OrdersDto ordersDto) {
        //dto转换
        Orders orders = BeanCopyUtils.copyBean(ordersDto, Orders.class);
        return ordersMapper.update(orders);
    }

    /**
     * 根据工程师id查询订单
     */
    @Override
    public List<OrdersVo> listMyOrders(Integer mid){
        //根据工程师id查询
        List<Orders> ordersList = ordersMapper.listMyOrders(mid);
        //将手机号添加到 orders 表
        List<Orders> collect = ordersList.stream()
                .map(orders -> orders.setCar(orders.getCustomer().getCar()))
                .collect(Collectors.toList());
        //转换vo
        List<OrdersVo> ordersVos = BeanCopyUtils.copyBeanList(collect, OrdersVo.class);
        return ordersVos;
    }
}
