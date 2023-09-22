package com.cfz.service.impl;

import com.cfz.entity.Customerservice;
import com.cfz.mapper.CustomerserviceMapper;
import com.cfz.service.CustomerserviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerserviceServiceImp implements CustomerserviceService {

    @Autowired
    CustomerserviceMapper customerMapper;

    @Override
    public Customerservice login(Customerservice customer) {
        return customerMapper.login(customer);
    }
}
