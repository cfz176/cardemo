package com.cfz.mapper;

import com.cfz.entity.Master;
import com.cfz.entity.MasterAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Master)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-18 09:49:20
 */
public interface MasterMapper {

    /**
     * 查询所有工程师 分页 条件 联工程师地址表
     *
     * @return
     */
    List<Master> findAll(Master master);

    /**
     * 添加工程师
     *
     * @param master
     * @return
     */
    Integer insertMaster(Master master);


    /**
     * 添加工程师
     *
     * @param master
     * @return
     */
    Integer updateMaster(Master master);



}

