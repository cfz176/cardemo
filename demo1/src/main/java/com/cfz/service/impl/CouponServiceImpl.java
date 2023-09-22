package com.cfz.service.impl;

import com.cfz.constants.SystemConstants;
import com.cfz.entity.Coupon;
import com.cfz.entity.dto.CouponDto;
import com.cfz.mapper.CouponMapper;
import com.cfz.mapper.CustomerCouponMapper;
import com.cfz.service.CouponService;
import com.cfz.utils.BeanCopyUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;


    @Autowired
    private CustomerCouponMapper customerCouponMapper;

    /**
     * 查询优惠券 分页 条件
     * @param couponDto
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Page<Coupon> selectList(CouponDto couponDto, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        //转换Dto
        Coupon coupon = BeanCopyUtils.copyBean(couponDto, Coupon.class);
        //只查询 del 为 0 的
        coupon.setDel(SystemConstants.DEL_DEFAULT);
        //查询优惠券
        Page<Coupon> couponPage = (Page<Coupon>) couponMapper.selectList(coupon);
        //返回查询到的优惠券
        return couponPage;
    }

    /**
     * 新增优惠券
     * @param coupon
     * @return
     */
    @Override
    public Integer insert(Coupon coupon) {
        coupon.setDel(SystemConstants.DEL_DEFAULT);
        return couponMapper.insert(coupon);
    }

    /**
     * 修改优惠券
     * @param coupon
     * @return
     */
    @Override
    public Integer update(Coupon coupon) {
        return couponMapper.update(coupon);
    }

    /**
     * 根据id查询优惠券
     * @param id
     * @return
     */
    @Override
    public Coupon selectById(Integer id) {
        return couponMapper.selectById(id);
    }

    @Override
    public List<Coupon> selectListByPhone(String phone) {
        return couponMapper.selectListByPhone(phone);
    }


}
