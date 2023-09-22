package com.cfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Customerservice)实体类
 *
 * @author makejava
 * @since 2023-07-14 13:24:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customerservice implements Serializable {
    private static final long serialVersionUID = -75180442821442273L;
    /**
     * 客服编号(自增)
     */
    private Integer id;
    /**
     * 客服名字
     */
    private String name;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}

