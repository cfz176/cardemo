package com.cfz.controller;

import com.cfz.annotation.SystemLog;
import com.cfz.entity.Customer;
import com.cfz.service.CustomerPhoneService;
import com.cfz.utils.JedisUtil;
import com.cfz.utils.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/customerPhone")
public class CustomerPhoneController {

    @Autowired
    CustomerPhoneService customerPhoneService;

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping("/goLogin")
    public String goLogin() {
        return "/html/customerPhone/html/login.html";
    }

    /**
     * 跳转我的优惠券
     * @return
     */
    @SystemLog(methods = "跳转我的优惠券页面",module = "用户手机端模块")
    @RequestMapping("/coupon")
    public String coupon() {
        return "/html/customerPhone/html/coupon.html";
    }

    /**
     * 获取验证码
     *
     * @return
     */
    @RequestMapping("/sendSms")
    @ResponseBody
    @SystemLog(methods = "获取验证码",module = "用户手机端模块")
    public String sendSms(String phone) {
        //生成随机验证码
        String verificationCode = SendSms.getVerificationCode(6);
        JedisUtil.putKey(phone, verificationCode);
        try {
            SendSms.sendMessage(phone, verificationCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return verificationCode;
    }

    /**
     * 根据验证码登录
     *
     * @param phone
     * @param code
     * @return
     */
    @SystemLog(methods = "登录",module = "用户手机端模块")
    @RequestMapping("/login")
    @ResponseBody
    public String login(String phone, String code, HttpSession httpSession) {
        //cong redis 中取出验证码
        String sCode = JedisUtil.gettKey(phone);
        if (!ObjectUtils.isEmpty(sCode) && sCode.equals(code)) {
            //查询用户
            Customer customer = customerPhoneService.phoneLogin(phone);
            //存入 session
            httpSession.setMaxInactiveInterval(24 * 3600);
            httpSession.setAttribute("phone", customer);
            return "index";
        }
        return "error";
    }

    /**
     * 跳转 主页
     *
     * @return
     */
    @SystemLog(methods = "跳转到主页",module = "用户手机端模块")
    @RequestMapping("/index")
    public String index() {
        return "/html/customerPhone/index.html";
    }

    /**
     * 跳转到 我的订单界面
     *
     * @return
     */
    @SystemLog(methods = "跳转到我的订单页面",module = "用户手机端模块")
    @RequestMapping("/order")
    public String order() {
        return "/html/customerPhone/html/order.html";
    }

}
