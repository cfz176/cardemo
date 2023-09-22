package com.cfz.controller;

import com.alibaba.fastjson.JSONArray;
import com.cfz.annotation.SystemLog;
import com.cfz.entity.Master;
import com.cfz.entity.Orders;
import com.cfz.entity.dto.OrdersDto;
import com.cfz.entity.vo.OrdersVo;
import com.cfz.entity.vo.PageVo;
import com.cfz.service.MasterPhoneService;
import com.cfz.service.MasterService;
import com.cfz.service.OrdersService;
import com.cfz.utils.PageUtil;
import com.cfz.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("/masterPhone")
public class MasterPhoneContrroller {

    @Autowired
    private MasterService masterService;

    @Autowired
    private MasterPhoneService masterPhoneService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 跳转到登陆页面
     * @return
     */
    @RequestMapping("/goLogin")
    public String goLogin() {
        return "/html/masterPhone/html/login.html";
    }

    /**
     * 登录方法
     * @param master
     * @return
     */
    @SystemLog(methods = "登录",module = "工程师手机端模块")
    @RequestMapping("/login")
    @ResponseBody
    public String login(Master master, HttpSession httpSession) {
        Master login = masterPhoneService.login(master);
        //判断用户名密码是否正确
        if (!ObjectUtils.isEmpty(login) && login.getDel().equals("0")) {
            //登陆成功 将工程师信息传入 session
            httpSession.setAttribute("masterInfo",login);
            httpSession.setAttribute("mid",login.getId());
            return "index";
        }
        return "error";
    }

    /**
     * 跳转首页
     * @return
     */
    @SystemLog(methods = "跳转到首页",module = "工程师手机端模块")
    @RequestMapping("/index")
    public String index() {
        return "/html/masterPhone/index.html";
    }

//    /**
//     * 提示工程师是否有新的订单
//     * @param httpSession
//     * @return
//     */
//    @RequestMapping("/myorder")
//    @ResponseBody
//    public String myorder(HttpSession httpSession) {
//        //从 session 中获取工程师信息
//        Master masterInfo = (Master) httpSession.getAttribute("masterPhone");
//        //查询该工程师的订单
//        if (ObjectUtils.isEmpty(masterInfo)) {
//            return "unfinished";
//        }
//        Master master = masterPhoneService.selectStatus(masterInfo.getId());
//        //判断订单状态
//        for (Orders orders : master.getOrders()) {
//            String status = orders.getStatus();
//            if (status.equals("0") || status.equals("1") || status.equals("2")) {
//                return "unfinished";
//            }
//        }
//        return "newOrder";
//    }

    /**
     * 跳转提示订单页面
     * @return
     */
    @RequestMapping("/async")
    public String async() {
        return "/html/masterPhone/tool/async.html";
    }

    /**
     * 跳转到我的订单页面
     * @return
     */
    @SystemLog(methods = "跳转到我的订单页面",module = "工程师手机端模块")
    @RequestMapping("/goMyOrder")
    public String goMyOrder() {
        return "/html/masterPhone/html/myOrder.html";
    }

    /**
     * 查询我的订单
     * @return
     */
    @SystemLog(methods = "查询我的订单",module = "工程师手机端模块")
    @RequestMapping("/myOrders")
    @ResponseBody
    public String myOrders(HttpSession httpSession) {
        //从 session 取出当前工程师信息
        Master masterInfo = (Master) httpSession.getAttribute("masterInfo");
        //根据工程师 id 查询订单
        List<OrdersVo> ordersVos = ordersService.listMyOrders(masterInfo.getId());
        return JSONArray.toJSONString(ordersVos);
    }

    /**
     * 异步处理
     * @param session
     * @return
     * @description：查看一下有没有自己的订单
     * 没有订单的情况下，查看是否有订单；
     * 有订单的情况下，是否订单被中途取消。
     */
    @RequestMapping("/myorder")
    @ResponseBody
    public Callable<String> callable(HttpSession session) {
        return new Callable<String>() {
            String unfinished = "";// 有未完成订单
            String newOrder = "";// 有新订单
            String phone = session.getAttribute("masterInfo") == null ? "" : session.getAttribute("masterInfo").toString();
            String mid = session.getAttribute("mid") == null ? "" : session.getAttribute("mid").toString();

            @Override
            public String call() throws Exception {
                for (int i = 0; i < 30; i++) {
                    unfinished = redisUtil.get("mid") == null ? "" : redisUtil.get("mid").toString();
                    newOrder = redisUtil.get("orders") == null ? "" : redisUtil.get("orders").toString();
                    if ((!unfinished.equals("")) && unfinished != null) {// 有未完成订单
                        System.out.println("unfinished:" + unfinished);
                        return "unfinished";
                    } else if ((!newOrder.equals("")) && newOrder != null) {// 有新订单
                        System.out.println("newOrder:" + newOrder);
                        return "newOrder";
                    }
                    Thread.sleep(1000);
                }
                return "";
            }
        };
    }

    /**
     * 跳转可接订单页面
     * @return
     */
    @SystemLog(methods = "跳转可解订单页面",module = "工程师手机端模块")
    @RequestMapping("/goGetOrder")
    public String goGetOrder() {
        return "/html/masterPhone/html/getOrder.html";
    }

    /**
     * 可接订单
     * @return
     */
    @SystemLog(methods = "查询可解订单列表",module = "工程师手机端模块")
    @RequestMapping("/getOrders")
    @ResponseBody
    public String getOrders() {
        return redisUtil.get("getOrders")+"";
    }


}
