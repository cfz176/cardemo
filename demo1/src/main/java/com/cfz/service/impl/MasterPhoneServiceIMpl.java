package com.cfz.service.impl;

import com.cfz.entity.Master;
import com.cfz.entity.Orders;
import com.cfz.mapper.MasterPhoneMapper;
import com.cfz.service.MasterPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterPhoneServiceIMpl implements MasterPhoneService {

    @Autowired
    private MasterPhoneMapper masterPhoneMapper;

    /**
     * 工程师登录
     * @param master
     * @return
     */
    @Override
    public Master login(Master master) {
        return masterPhoneMapper.login(master);
    }

    @Override
    public Master selectStatus(Integer id) {
        return masterPhoneMapper.selectStatus(id);
    }

}
