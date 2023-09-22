package com.cfz.service.impl;

import com.cfz.constants.SystemConstants;
import com.cfz.entity.Customer;
import com.cfz.mapper.CustomerMapper;
import com.cfz.mapper.CustomerPhoneMapper;
import com.cfz.service.CustomerPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
public class CustomerPhoneServiceImpl implements CustomerPhoneService {

    @Autowired
    CustomerPhoneMapper customerPhoneMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public Customer phoneLogin(String phone) {
        Customer customer = null;
        customer = customerPhoneMapper.listCustomer(phone);
        if (ObjectUtils.isEmpty(customer)) {
            customer = new Customer();
            customer.setPhone(phone);
            customer.setDel(SystemConstants.DEL_DEFAULT);
            customer.setCreatetime(new Date());
            customerMapper.add(customer);
        }
        return customer;
    }
}
