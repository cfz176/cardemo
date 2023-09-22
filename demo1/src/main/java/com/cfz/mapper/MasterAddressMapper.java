package com.cfz.mapper;

import com.cfz.entity.MasterAddress;

public interface MasterAddressMapper {

    /**
     * 添加工程师地址信息
     * @return
     */
    Integer insertMasterAddress(MasterAddress masterAddress);

    Integer update(MasterAddress master);
}
