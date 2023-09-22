package com.cfz.service.impl;

import com.cfz.constants.SystemConstants;
import com.cfz.entity.CustomerCoupon;
import com.cfz.mapper.CustomerCouponMapper;
import com.cfz.service.CustomerCouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (CustomerCoupon)表服务实现类
 *
 * @author makejava
 * @since 2023-07-26 11:13:59
 */
@Service
public class CustomerCouponServiceImpl implements CustomerCouponService {
    @Resource
    private CustomerCouponMapper customerCouponMapper;

    /**
     * 批量分发优惠券
     * @param customer_id
     * @param coupon_id
     * @return
     */
    @Override
    public Integer couponBatch(Integer[] customer_id, Integer coupon_id) {
        CustomerCoupon customerCoupon = new CustomerCoupon();
        //添加当前时间
        customerCoupon.setAddtime(new Date());
        //要添加的优惠券id
        customerCoupon.setCouponId(coupon_id);
        //设置默认 del
        customerCoupon.setDel(SystemConstants.DEL_DEFAULT);
        //设置默认状态 status
        customerCoupon.setStatus(SystemConstants.CUSTOMER_COUPON_STATUS_DEFAULT);
        return customerCouponMapper.addCoupon(customer_id,customerCoupon);
    }
}
