package com.cfz.service;

import com.cfz.entity.Customer;

public interface CustomerPhoneService {

    /**
     * 验证码登录
     * @return
     */
    Customer phoneLogin(String phone);
}
