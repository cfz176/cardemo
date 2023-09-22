package com.cfz.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (CustomerCoupon)实体类
 *
 * @author makejava
 * @since 2023-07-26 11:01:50
 */
public class CustomerCoupon implements Serializable {
    private static final long serialVersionUID = 662727021624943200L;
    /**
     * 编号 自增
     */
    private Integer id;
    /**
     * 客户编号 客户外键
     */
    private Integer customerId;
    /**
     * 优惠券编号 优惠券外键
     */
    private Integer couponId;
    /**
     * 新增时间
     */
    private Date addtime;
    /**
     * 状态 0未使用 1已使用
     */
    private String status;
    /**
     * 是否删除 0 未删除1 已删除
     */
    private String del;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

}

