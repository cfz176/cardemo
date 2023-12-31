package com.cfz.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Customer)实体类
 *
 * @author makejava
 * @since 2023-07-17 09:55:34
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = -36658459089954170L;
    /**
     * 客户编号(自增)
     */
    private Integer id;
    /**
     * 客户姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 身份证
     */
    private String idcard;
    /**
     * 积分
     */
    private String score;
    /**
     * 车牌号
     */
    private String car;
    /**
     * 性别
     */
    private String sex;
    /**
     * 是否显示 默认为0
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

}

