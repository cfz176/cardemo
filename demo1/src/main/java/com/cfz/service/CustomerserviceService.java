package com.cfz.service;

import com.cfz.entity.Customerservice;

/**
 * (Customerservice)表服务接口
 *
 * @author makejava
 * @since 2023-07-14 13:24:27
 */
public interface CustomerserviceService {

    /**
     * 登陆查询
     * @param customerservice
     * @return
     */
    Customerservice login(Customerservice customerservice);
}
