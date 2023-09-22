package com.cfz.mapper;

import com.cfz.entity.Coupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Coupon)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-26 10:04:40
 */
public interface CouponMapper {


    /**
     * 查询优惠券 分页 条件
     * @param coupon
     * @return
     */
    List<Coupon> selectList(Coupon coupon);

    /**
     * 新增优惠券
     * @param coupon
     * @return
     */
    Integer insert(Coupon coupon);

    /**
     * 修改优惠券
     * @param coupon
     * @return
     */
    Integer update(Coupon coupon);

    /**
     * 根据id查询优惠券
     * @param id
     * @return
     */
    Coupon selectById(Integer id);

    List<Coupon> selectListByPhone(String phone);
}

