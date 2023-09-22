package com.cfz.controller;

import com.cfz.annotation.SystemLog;
import com.cfz.entity.Customerservice;
import com.cfz.service.CustomerserviceService;
import com.cfz.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Controller
@RequestMapping("/customerService")
public class CustomerserviceController {

    @Autowired
    private CustomerserviceService customerService;

    @RequestMapping("/goLogin")
    public String goLogin() {
        return "/html/customerService/login.html";
    }

    /**
     * 登录方法
     *
     * @param customer
     * @param verifyCode
     * @return
     */
    @SystemLog(methods = "登录",module = "登录模块")
    @ResponseBody
    @RequestMapping("/login")
    public String login(HttpSession httpSession, Customerservice customer, String verifyCode) {
        //判断验证码是否为空
        if (!verifyCode.isEmpty()) {
            //将输入的验证码转为小写
            String lowerCase = verifyCode.toLowerCase();
            //从 session 中取出验证码
            String verCode = (String) httpSession.getAttribute("verCode");
            //判断验证码是否合法
            if (lowerCase.equals(verCode)) {
                //如果合法，判断用户名密码是否正确
                Customerservice login = customerService.login(customer);
                if (!ObjectUtils.isEmpty(login)) {
                    //用户名密码正确
                    httpSession.setAttribute("customer",login);
                    return "index";
                }
                //用户名密码错误
                return "showLogin";
            }
            //不合法
        }
        return "showLogin";
    }

    /**
     * 跳转到后台主页
     * @return
     */
    @SystemLog(methods = "跳转主页",module = "登录模块")
    @GetMapping("/index")
    public String index() {
        return "/html/customerService/index.html";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @SystemLog(methods = "退出登录",module = "登录模块")
    @RequestMapping("/logOut")
    public String logOut(HttpSession session) {
        if (!ObjectUtils.isEmpty(session.getAttribute("customer"))){
            session.removeAttribute("customer");
        }
        return "/customerService/goLogin";
    }

    @RequestMapping("/generateCheckCode")
    public void generateCheckCode(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("验证码");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        // 生成图片
        int w = 95, h = 38;
        try {
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
