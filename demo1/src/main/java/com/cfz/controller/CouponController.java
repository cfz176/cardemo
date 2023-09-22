package com.cfz.controller;

import com.alibaba.fastjson.JSONArray;
import com.cfz.annotation.SystemLog;
import com.cfz.entity.Coupon;
import com.cfz.entity.Customer;
import com.cfz.entity.dto.CouponDto;
import com.cfz.entity.vo.CouponVo;
import com.cfz.service.CouponService;
import com.cfz.service.CustomerCouponService;
import com.cfz.utils.BeanCopyUtils;
import com.cfz.utils.PageUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 优惠券接口
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;


    @Autowired
    private CustomerCouponService customerCouponService;

    /**
     * 跳转优惠券界面
     *
     * @return
     */

    @SystemLog(methods = "跳转优惠券",module = "优惠券模块")
    @RequestMapping("/selectShow")
    public String selectShow() {
        return "/html/coupon/table.html";
    }

    /**
     * 查询优惠券 分页 条件
     *
     * @param couponDto
     * @param page
     * @param limit
     * @return
     */
    @SystemLog(methods = "查询优惠券列表",module = "优惠券模块")
    @RequestMapping("/selectList")
    @ResponseBody
    public String selectList(CouponDto couponDto, Integer page, Integer limit) {
        //如果根据名称查询
        if (!ObjectUtils.isEmpty(couponDto.getName())) {
            couponDto.setName(couponDto.getName().trim());
        }
        //根据条件分页查询优惠券;
        Page<Coupon> couponPage = couponService.selectList(couponDto, page, limit);
        //转换Vo
        List<CouponVo> couponVos = BeanCopyUtils.copyBeanList(couponPage.getResult(), CouponVo.class);
        //设置返回格式
        PageUtil pageUtil = new PageUtil();
        pageUtil.setData(JSONArray.toJSONString(couponVos));
        pageUtil.setCode("0");
        pageUtil.setMsg("");
        pageUtil.setCount(couponPage.getTotal() + "");
        return pageUtil.toString();
    }

    /**
     * 跳转发放优惠券页面
     * @return
     */
    @SystemLog(methods = "跳转发放优惠券页面",module = "优惠券模块")
    @GetMapping("/send")
    public String send() {
        return "/html/coupon/customer/table.html";
    }

    /**
     * 发放优惠券
     * @param customer_id
     * @param coupon_id
     * @return
     */
    @SystemLog(methods = "发放优惠券",module = "优惠券模块")
    @PostMapping("/send")
    @ResponseBody
    public String send1(Integer[] customer_id,Integer coupon_id) {
        customerCouponService.couponBatch(customer_id,coupon_id);
        return "";
    }

    /**
     * 跳转新增
     * @return
     */
    @SystemLog(methods = "跳转新增页面",module = "优惠券模块")
    @GetMapping("/goAdd")
    public String goAdd() {
        return "/html/coupon/addCoupon.html";
    }

    /**
     * 新增订单
     * @param coupon
     * @return
     */

    @SystemLog(methods = "添加优惠券",module = "优惠券模块")
    @PostMapping("/add")
    @ResponseBody
    public String add(Coupon coupon) {
        return couponService.insert(coupon)+"";
    }

    /**
     * 跳转修改页面
     * @return
     */
    @SystemLog(methods = "跳转修改页面",module = "优惠券模块")
    @GetMapping("/goUpdate")
    public String goUpdate() {
        return "/html/coupon/updateCoupon.html";
    }

    /**
     * 修改优惠券
     * @param coupon
     * @return
     */
    @SystemLog(methods = "修改优惠券",module = "优惠券模块")
    @PostMapping("/update")
    @ResponseBody
    public String update(Coupon coupon) {
        return couponService.update(coupon)+"";
    }

    /**
     * 查询优惠券列表
     * @param id
     * @return
     */
    @SystemLog(methods = "查询优惠券列表",module = "优惠券模块")
    @RequestMapping
    @ResponseBody
    public String selectById(Integer id) {
        return JSONArray.toJSONString(couponService.selectById(id));
    }

    /**
     * 查询优惠券列表
     * @param httpSession
     * @return
     */
    @SystemLog(methods = "查询优惠券列表",module = "优惠券模块")
    @RequestMapping("/selectListByPhone")
    @ResponseBody
    public String selectListByPhone(HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("phone");
        return JSONArray.toJSONString(couponService.selectListByPhone(customer.getPhone()));
    }

}
