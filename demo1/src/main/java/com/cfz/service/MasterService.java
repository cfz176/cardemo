package com.cfz.service;

import com.cfz.entity.Master;
import com.cfz.entity.vo.PageVo;

import java.util.List;

/**
 * (Master)表服务接口
 *
 * @author makejava
 * @since 2023-07-18 09:49:21
 */
public interface MasterService {

    /**
     * 查询所有接口
     *
     * @param master
     * @return
     */
    PageVo findAll(Master master, Integer page, Integer limit);

    /**
     * 添加接口
     * @param master
     * @return
     */
    Integer insertMaster(Master master);

    /**
     * 修改接口
     *
     * @param master
     * @return
     */
    Integer update(Master master);

}
