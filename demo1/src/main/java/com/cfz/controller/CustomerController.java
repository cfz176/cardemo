package com.cfz.controller;

import com.alibaba.fastjson.JSONArray;
import com.cfz.annotation.SystemLog;
import com.cfz.entity.Customer;
import com.cfz.entity.dto.CustomerDto;
import com.cfz.entity.vo.PageVo;
import com.cfz.service.CustomerService;
import com.cfz.utils.BeanCopyUtils;
import com.cfz.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 跳转用户管理页面
     * @return
     */
    @SystemLog(methods = "跳转用户管理页面",module = "用户管理模块")
    @GetMapping("/selectShow")
    public String selectShow() {
        return "/html/customer/table.html";
    }

    /**
     * 查询用户列表
     * @param page
     * @param limit
     * @param customer
     * @return
     */
    @SystemLog(methods = "查询用户列表",module = "用户管理模块")
    @PostMapping("/selectList")
    @ResponseBody
    public String selectList(Integer page, Integer limit, Customer customer) {
        PageVo pageVo = customerService.selectList(page, limit, customer);

        PageUtil pageUtil = new PageUtil();
        pageUtil.setCode(null);
        pageUtil.setMsg("");
        pageUtil.setCount(pageVo.getTotal() + "");
        pageUtil.setData(JSONArray.toJSONString(pageVo.getRows()));

        return pageUtil.toString();
    }

    /**
     * 跳转添加用户页面
     * @return
     */
    @SystemLog(methods = "跳转添加用户页面",module = "用户管理模块")
    @GetMapping("/goAdd")
    public String goAdd() {
        return "/html/customer/addCustomer.html";
    }

    /**
     * 添加用户
     * @param customerDto
     * @return
     */
    @SystemLog(methods = "添加用户",module = "用户管理模块")
    @PostMapping("/add")
    @ResponseBody
    public String add(@Validated CustomerDto customerDto) {
        Customer customer = BeanCopyUtils.copyBean(customerDto, Customer.class);
        return String.valueOf(customerService.add(customer));
    }

    /**
     * 跳转修改页面
     * @return
     */
    @SystemLog(methods = "跳转修改页面",module = "用户管理模块")
    @GetMapping("/goUpdate")
    public String goUpdate() {
        return "/html/customer/updateCustomer.html";
    }

    /**
     * 修改页面
     * @param customerDto
     * @return
     */
    @SystemLog(methods = "修改页面",module = "用户管理模块")
    @PostMapping("/update")
    @ResponseBody
    public String update(CustomerDto customerDto) {
        Customer customer = BeanCopyUtils.copyBean(customerDto, Customer.class);
        return customerService.update(customer)+"";
    }

}
