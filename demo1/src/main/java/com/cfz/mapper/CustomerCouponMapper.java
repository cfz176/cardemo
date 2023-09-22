package com.cfz.mapper;

import com.cfz.entity.CustomerCoupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (CustomerCoupon)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-26 11:01:49
 */
public interface CustomerCouponMapper {

    /**
     * 批量发放优惠券
     * @param customer_id
     * @param customerCoupon
     * @return
     */
    @Transactional
    Integer addCoupon(@Param("customerId") Integer[] customer_id,@Param("customerCoupon")CustomerCoupon customerCoupon);

}

