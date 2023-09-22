package com.cfz.handler;

import com.cfz.entity.Customerservice;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从 session 取出数据
        StringBuffer requestURL = request.getRequestURL();
        Customerservice customer = (Customerservice) request.getSession().getAttribute("customer");
        if (!ObjectUtils.isEmpty(customer)) {
            return true;
        }
        request.getRequestDispatcher("/customerService/goLogin").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
