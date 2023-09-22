package com.cfz.service.impl;

import com.cfz.constants.SystemConstants;
import com.cfz.entity.Master;
import com.cfz.entity.MasterAddress;
import com.cfz.entity.vo.PageVo;
import com.cfz.mapper.MasterAddressMapper;
import com.cfz.mapper.MasterMapper;
import com.cfz.service.MasterService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Master)表服务实现类
 *
 * @author makejava
 * @since 2023-07-18 09:49:21
 */
@Service("masterService")
public class MasterServiceImpl implements MasterService {

    @Autowired
    MasterMapper masterMapper;

    @Autowired
    MasterAddressMapper masterAddressMapper;

    /**
     * 查询所有
     *
     * @param master
     * @return
     */
    @Override
    public PageVo findAll(Master master, Integer page, Integer limit) {
        //只查询 del 为 0 的数据
        master.setDel("0");
        PageHelper.startPage(page, limit);
        //查询
        List<Master> masterList = masterMapper.findAll(master);
        //转换Vo
        Page<Master> masterPage = (Page<Master>) masterList;
        return new PageVo(masterPage.getTotal(), masterPage.getResult());
    }

    @Override
//    @Transactional
    public Integer insertMaster(Master master) {

        //添加工程师 获取新增的工程师 id
        //默认 del 为 0
        master.setDel(SystemConstants.DEL_DEFAULT);
        master.setDid("0");
        Integer mid = masterMapper.insertMaster(master);
        //地址信息
        MasterAddress masterAddress = new MasterAddress();
        masterAddress.setLng("108.840014");
        masterAddress.setLat("34.217209");
        masterAddress.setMid(master.getId());
        masterAddress.setStatus(SystemConstants.MASTER_STATUS_DEFAULT);

        //添加工程师地址信息
        Integer insertMasterAddress = masterAddressMapper.insertMasterAddress(masterAddress);
        return insertMasterAddress;
    }

    /**
     * 修改工程师信息
     *
     * @param master
     * @return
     */
    @Override
    public Integer update(Master master) {
        return masterMapper.updateMaster(master);
    }

}
