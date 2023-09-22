package com.cfz.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Orders)实体类
 *
 * @author makejava
 * @since 2023-07-19 10:24:56
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto implements Serializable {
    private static final long serialVersionUID = -77508568462425603L;
    /**
     * 订单号
     */
    private Integer id;
    /**
     * 下单手机号 关联客户表手机号
     */
    private String phone;
    /**
     * 工程师编号
     */
    private Integer mid;
    /**
     * 订单纬度
     */
    private String lng;
    /**
     * 订单经度
     */
    private String lat;
    /**
     * 下订单时间
     */
    private Date createtime;
    /**
     * 接单时间
     */
    private Date updatetime;
    /**
     * 完成订单时间
     */
    private Date endtime;
    /**
     * 救援内容
     */
    private String contents;
    /**
     * 订单状态 0刚下单，1已接单，2已到达正在修，3已完成，4已评价
     */
    private String status;
    /**
     * 救援地址
     */
    private String address;

    /**
     * 附带内容
     */
    private String attached;
    /**
     * 金额
     */
    private String cost;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 客户车牌号
     */
    private String car;
}

