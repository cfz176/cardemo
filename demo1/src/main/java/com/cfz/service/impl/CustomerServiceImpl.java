package com.cfz.service.impl;

import com.cfz.entity.Customer;
import com.cfz.entity.vo.PageVo;
import com.cfz.mapper.CustomerMapper;
import com.cfz.service.CustomerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Customer)表服务实现类
 *
 * @author makejava
 * @since 2023-07-17 09:55:34
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    /**
     * 查询用户 分页 条件
     * @param pageSize
     * @param pageNum
     * @param customer
     * @return
     */
    @Override
    public PageVo selectList(Integer pageSize, Integer pageNum, Customer customer) {
        //只查询 del 为 0 的
        customer.setDel("0");
        //设置分页
        PageHelper.startPage(pageSize, pageNum);
        //分页查询
        Page<Customer> customerPage = (Page<Customer>) customerMapper.selectList(customer);
        //封装vo返回
        return new PageVo(customerPage.getTotal(),customerPage.getResult());
    }

    /**
     * 新增用户
     * @param customer
     * @return
     */
    @Override
    public Integer add(Customer customer) {
        return customerMapper.add(customer);
    }

    /**
     * 修改用户
     * @param customer
     * @return
     */
    @Override
    public Integer update(Customer customer) {
        return customerMapper.update(customer);
    }

}
