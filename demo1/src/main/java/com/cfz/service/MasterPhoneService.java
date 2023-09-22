package com.cfz.service;

import com.cfz.entity.Master;

import java.util.List;

public interface MasterPhoneService {

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
