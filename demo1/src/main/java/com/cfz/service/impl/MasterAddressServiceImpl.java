package com.cfz.service.impl;

import com.cfz.entity.MasterAddress;
import com.cfz.mapper.MasterAddressMapper;
import com.cfz.service.MasterAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterAddressServiceImpl implements MasterAddressService {

    @Autowired
    private MasterAddressMapper masterAddressMapper;

    @Override
    public Integer insertMasterAddress(MasterAddress masterAddress) {
        return masterAddressMapper.insertMasterAddress(masterAddress);
    }

    @Override
    public Integer update(MasterAddress master) {
        return masterAddressMapper.update(master);
    }
}
