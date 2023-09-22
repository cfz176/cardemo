package com.cfz.service;

import com.cfz.entity.Coupon;
import com.cfz.entity.dto.CouponDto;
import com.github.pagehelper.Page;

import java.util.List;

public interface CouponService {
    Page<Coupon> selectList(CouponDto couponDto, Integer page, Integer limit);


    Integer insert(Coupon coupon);

    Integer update(Coupon coupon);

    /**
     * 根据id查询优惠券
     * @param id
     * @return
     */
    Coupon selectById(Integer id);

    List<Coupon> selectListByPhone(String phone);
}
