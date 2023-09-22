package com.cfz.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {

    /**
     * 优惠券名称
     */
    private String name;
    /**
     *  开始时间附带内容
     */
    private Date begin;
    /**
     * 结束时间
     */
    private Date end;
    /**
     * 打折百分比 例如：90%就是打9折
     */
    private String discount;
    /**
     * 备注
     */
    private String remark;
}
