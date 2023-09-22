package com.cfz.entity.vo;

import com.cfz.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class OrdersVo implements Serializable {
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
     * 下订单时间
     */
    private Date createtime;

    /**
     * 订单状态 0刚下单，1已接单，2已到达正在修，3已完成，4已评价
     */
    private String status;
    /**
     * 救援地址
     */
    private String address;
    /**
     * 订单纬度
     */
    private String lng;
    /**
     * 订单经度
     */
    private String lat;

    /**
     * 金额
     */
    private String cost;

    private String name;
    private String car;
}

