package com.cfz.controller;

import com.alibaba.fastjson.JSONArray;
import com.cfz.annotation.SystemLog;
import com.cfz.entity.Customer;
import com.cfz.entity.Master;
import com.cfz.entity.MasterAddress;
import com.cfz.entity.Orders;
import com.cfz.entity.dto.OrdersDto;
import com.cfz.entity.vo.OrdersVo;
import com.cfz.entity.vo.PageVo;
import com.cfz.service.MasterAddressService;
import com.cfz.service.MasterService;
import com.cfz.service.OrdersService;
import com.cfz.utils.MapUtils;
import com.cfz.utils.PageUtil;
import com.cfz.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MasterAddressService masterAddressService;

    /**
     * 跳转订单页面
     *
     * @return
     */
    @SystemLog(methods = "跳转订单页面", module = "订单管理模块")
    @RequestMapping("/selectShow")
    public String selectShow() {
        return "/html/orders/table.html";
    }

    /**
     * 查询所有订单
     *
     * @param ordersDto
     * @param page
     * @param limit
     * @return
     */
    @SystemLog(methods = "查询订单列表", module = "订单管理模块")
    @RequestMapping("/selectList")
    @ResponseBody
    public String selectList(OrdersDto ordersDto, Integer page, Integer limit) {
        //查询订单
        PageVo pageVo = ordersService.findAll(ordersDto, page, limit);
        //转换Vo
//        List<OrdersVo> ordersVos = BeanCopyUtils.copyBeanList(pageVo.getRows(), OrdersVo.class);

//        //将经度纬度转换为地址
//        List<OrdersVo> collect = ordersVos.stream()
//                .map(ordersVo -> {
//                    OrdersVo vo = null;
//                    try {
//                        vo = ordersVo.setAddress(LngAndLatUtil.getLngAndLat(ordersVo.getLng(), ordersVo.getLat()));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    return vo;
//                }).collect(Collectors.toList());

        //设置返回信息
        PageUtil pageUtil = new PageUtil();
        pageUtil.setData(JSONArray.toJSONString(pageVo.getRows()));
        pageUtil.setCode("0");
        pageUtil.setMsg("");
        pageUtil.setCount(pageVo.getTotal() + "");
        return pageUtil.toString();
    }

    /**
     * 跳转新增页面
     *
     * @return
     */
    @SystemLog(methods = "跳转到新增页面", module = "订单管理模块")
    @RequestMapping("/goAdd")
    public String goAdd() {
        return "/html/orders/addOrders.html";
    }

    /**
     * 添加订单 后台 客户
     *
     * @param ordersDto
     * @param httpSession
     * @return
     */
    @SystemLog(methods = "添加", module = "订单管理模块")
    @RequestMapping("/add")
    @ResponseBody
    public String add(OrdersDto ordersDto, HttpSession httpSession) {
        if (ObjectUtils.isEmpty(ordersDto.getPhone())) {
            Customer customer = (Customer) httpSession.getAttribute("phone");
            ordersDto.setPhone(customer.getPhone());
        }
        List<Orders> ordersList = ordersService.selectByStatus(ordersDto.getPhone(), ordersDto.getContents());
        if (!ObjectUtils.isEmpty(ordersList)) {
            return "您有未完成的订单";
        }
        return ordersService.insertOrders(ordersDto) + "";
    }

    /**
     * 跳转修改页面
     *
     * @return
     */
    @SystemLog(methods = "跳转修改页面", module = "订单管理模块")
    @RequestMapping("/goUpdate")
    public String goUpdate() {
        return "/html/orders/updateOrders.html";
    }

    /**
     * 修改订单
     *
     * @return
     */
    @SystemLog(methods = "修改订单", module = "订单管理模块")
    @RequestMapping("/update")
    @ResponseBody
    public String update(OrdersDto ordersDto, HttpSession httpSession) {
        Integer update = ordersService.update(ordersDto);
        if (update == 1 && redisUtil.get("mid") != null) {
            OrdersDto ordersDto1 = new OrdersDto();
            ordersDto1.setMid((Integer) httpSession.getAttribute("mid"));
            PageVo all = ordersService.findAll(ordersDto1, 1, 999);
            List<OrdersVo> rows = all.getRows();
            for (OrdersVo orders : rows) {
                if (orders.getStatus().equals(1) || orders.getStatus().equals(2)) {
                    return update + "";
                }
            }

            //修改工程师状态
            MasterAddress masterAddress = new MasterAddress();
            masterAddress.setStatus("1");
            masterAddress.setMid((Integer) redisUtil.get("mid"));
            masterAddressService.update(masterAddress);
            //删除判断订单的 key
            redisUtil.del("mid");
        }
        return update + "";
    }

    /**
     * 计费
     *
     * @return
     */
    @RequestMapping("/getMoney")
    @ResponseBody
    public String getMoney(String lng, String lat) {
        String my_lng = "108.967933";//指定我们店面所在地址坐标
        String my_lat = "34.249323"; //"123"-->123  Integer.parse
        double money = MapUtils.algorithm(Double.valueOf(my_lng), Double.valueOf(my_lat), Double.valueOf(lng), Double.valueOf(lat)) / 100;
        long round = Math.round(money);
        return round + "";
    }

    /**
     * 跳转修改状态页面
     *
     * @return
     */
    @SystemLog(methods = "跳转修改状态页面", module = "订单管理模块")
    @RequestMapping("/goUpdateStatus")
    public String goUpdateStatus() {
        return "/html/orders/updateStatus.html";
    }

    /**
     * 修改状态
     *
     * @param id
     * @param status
     * @return
     */
    @SystemLog(methods = "修改状态", module = "订单管理模块")
    @RequestMapping("/updateStatus")
    @ResponseBody
    public String updateStatus(Integer id, String status) {
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setId(id);
        ordersDto.setStatus(status);
        return ordersService.update(ordersDto) + "";
    }

    /**
     * 客户根据手机号查询订单
     *
     * @return
     */
    @SystemLog(methods = "根据手机号查询订单", module = "订单管理模块")
    @RequestMapping("/selectListByPhone")
    @ResponseBody
    public String selectListByPhone(HttpSession httpSession) {
        //取出 手机号
        Customer customer = (Customer) httpSession.getAttribute("phone");
        //查询订单
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setPhone(customer.getPhone());
        PageVo pageVo = ordersService.findAll(ordersDto, 1, 10);
        return JSONArray.toJSONString(pageVo.getRows());
    }

    /**
     * 跳转指派订单页面
     *
     * @return
     */
    @SystemLog(methods = "跳转指派订单页面", module = "订单管理模块")
    @GetMapping("/sendMaster")
    public String sendMaster() {
        return "/html/orders/master/table.html";
    }

    /**
     * 指派订单
     *
     * @return
     */
    @SystemLog(methods = "指派订单", module = "订单管理模块")
    @PostMapping("/sendMaster")
    @ResponseBody
    public String sendMaster1(Integer mid, Integer id, HttpSession httpSession) {
        Master masterInfo = (Master) httpSession.getAttribute("masterInfo");
        //后台派单
        OrdersDto ordersdto = new OrdersDto();
        //添加要指派的订单和被指派的工程师
        ordersdto.setMid(mid);
        ordersdto.setId(id);
        ordersdto.setStatus("1");
        if (!ObjectUtils.isEmpty(masterInfo)) {
            //工程师派单
            ordersdto.setMid(masterInfo.getId());
            mid = masterInfo.getId();
        }
        //指派订单
        Integer update = ordersService.update(ordersdto);
        //派单成功
        redisUtil.set("mid", mid);
        if (update.equals(1)) {
            //修改工程师状态
            MasterAddress masterAddress = new MasterAddress();
            masterAddress.setStatus("0");
            masterAddress.setMid(mid);
            masterAddressService.update(masterAddress);
        }
        return update + "";
    }

}
