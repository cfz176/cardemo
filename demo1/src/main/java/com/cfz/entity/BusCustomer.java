package com.cfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (BusCustomer)实体类
 *
 * @author makejava
 * @since 2023-07-28 12:27:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusCustomer implements Serializable {
    private static final long serialVersionUID = -39630107246933652L;

    /**
     * 地区
     */
    private String name;

    /**
     * 地区人数
     */
    private Integer value;

}

