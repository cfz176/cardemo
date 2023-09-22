package com.cfz.service;

import com.cfz.entity.MasterAddress;

public interface MasterAddressService {

    /**
     * 添加工程师地址信息接口
     * @param masterAddress
     * @return
     */
    Integer insertMasterAddress(MasterAddress masterAddress);

    Integer update(MasterAddress master);
}
