package com.cfz.mapper;

import com.cfz.entity.Customer;

public interface CustomerPhoneMapper {

    /**
     * 验证码登录
     * @return
     */
    Customer listCustomer(String phone);
}
