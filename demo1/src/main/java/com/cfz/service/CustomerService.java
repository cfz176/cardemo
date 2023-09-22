package com.cfz.service;

import com.cfz.entity.Customer;
import com.cfz.entity.vo.PageVo;

import java.util.List;

/**
 * (Customer)表服务接口
 *
 * @author makejava
 * @since 2023-07-17 09:55:34
 */
public interface CustomerService {

    /**
     * 分页查询接口
     * @return
     */
    PageVo selectList(Integer pageSize, Integer pageNum, Customer customer);

    Integer add(Customer customer);

    Integer update(Customer customer);
}
