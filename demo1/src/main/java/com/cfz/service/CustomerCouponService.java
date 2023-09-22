package com.cfz.service;

import com.cfz.entity.CustomerCoupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (CustomerCoupon)表服务接口
 *
 * @author makejava
 * @since 2023-07-26 11:13:44
 */
public interface CustomerCouponService {

    Integer couponBatch(Integer[] customer_id, Integer coupon_id);

}
