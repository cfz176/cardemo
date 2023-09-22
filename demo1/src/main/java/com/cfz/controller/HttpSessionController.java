package com.cfz.controller;

import com.cfz.entity.Customer;
import com.cfz.entity.Master;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class HttpSessionController {

    /**
     * 获取手机端登陆后的 信息
     * @return
     */
    @RequestMapping("/getSession")
    @ResponseBody
    public String getSession(HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("phone");
        if (!ObjectUtils.isEmpty(customer.getPhone())) {
            return "success";
        }
        Master master = (Master) httpSession.getAttribute("masterInfo");
        if (!ObjectUtils.isEmpty(master)) {
            return "success";
        }
        return "error";
    }
}
