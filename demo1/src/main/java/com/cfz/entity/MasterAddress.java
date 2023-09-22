package com.cfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (MasterAddress)实体类
 *
 * @author makejava
 * @since 2023-07-18 09:57:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterAddress implements Serializable {
    private static final long serialVersionUID = 901393729029204812L;
    /**
     * 地址编号 自增
     */
    private Integer id;
    /**
     * 纬度
     */
    private String lng;
    /**
     * 经度
     */
    private String lat;
    /**
     * 工程师编号 外键
     */
    private Integer mid;
    /**
     * 状态 0在忙、1空闲、2请假
     */
    private String status;

}

