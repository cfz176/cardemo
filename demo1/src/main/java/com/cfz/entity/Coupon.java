package com.cfz.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (Coupon)实体类
 *
 * @author makejava
 * @since 2023-07-26 10:04:41
 */
public class Coupon implements Serializable {
    private static final long serialVersionUID = 620786204036921279L;
    /**
     * 优惠券编号 自增
     */
    private Integer id;
    /**
     * 优惠券名称 
     */
    private String name;
    /**
     *  开始时间附带内容
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begin;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;
    /**
     * 打折百分比 例如：90%就是打9折
     */
    private String discount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否删除
     */
    private String del;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

}

