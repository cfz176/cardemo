package com.cfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusRent {

    /**
     * 月份
     */
    private Integer month;

    /**
     * 销售额
     */
    private Double price;

}
