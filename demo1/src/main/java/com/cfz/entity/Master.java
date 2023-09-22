package com.cfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * (Master)实体类
 *
 * @author makejava
 * @since 2023-07-18 09:49:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Master implements Serializable {
    private static final long serialVersionUID = -51326523275080940L;
    /**
     * 工程师编号(自增)
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private String age;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 所属部门编号
     */
    private String did;

    private String phone;
    /**
     * 是否删除 默认为0
     */
    private String del;

    private MasterAddress masterAddress;

    private List<Orders> orders;


}

