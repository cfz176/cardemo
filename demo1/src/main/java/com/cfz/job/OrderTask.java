package com.cfz.job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cfz.entity.Orders;
import com.cfz.mapper.OrdersTeskMapper;
import com.cfz.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 定期把订单同步至Redis数据库。
 * @author AKU
 *
 */
@Component
public class OrderTask {
	@Autowired
	OrdersTeskMapper ordersTeskMapper;
	@Autowired
	RedisUtil redisUtil;

	DateFormat df = new SimpleDateFormat("HH:mm:ss");
    /**
     * @throws Exception 
     * @auth: xing
     * @return：void
     * @description:同步订单 1 秒一次 （定时器）
     * 业务只提示 是否有新订单，不用记录订单具体详情
     */
	@Async
//    @Scheduled(cron = "*/3 * * * * ?")
    public void asyncOrder() throws Exception{
		try {
	    	List<Orders> list = ordersTeskMapper.findOrders();//读取 数据库中的 status 为 0 的订单
			List<Orders> collect = list.stream()
					.map(orders -> orders.setCar(orders.getCustomer().getCar()))
					.collect(Collectors.toList());
			System.out.println("======="+collect.size());
	    	redisUtil.set("getOrders", collect.size()==0?"":JSON.toJSONString(collect));//有订单就存入进去 ，
	    	redisUtil.set("orders", collect.size()==0?"":collect.size()+"");//没有订单就是“”，有订单就不是0
		}catch (Exception e) {
			System.out.println(e.toString());
		}
    }

}