package com.cfz.mapper;

import com.cfz.entity.Master;
import com.cfz.entity.Orders;

import java.util.List;

public interface MasterPhoneMapper {

    /**
     * 工程师登录
     * @param master
     * @return
     */
    Master login(Master master);

    /**
     * 提示工程师是否可以接订单
     *
     * @return
     */
    Master selectStatus(Integer id);



}
