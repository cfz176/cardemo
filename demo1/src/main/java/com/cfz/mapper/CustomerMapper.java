package com.cfz.mapper;

import com.cfz.entity.Customer;

import java.util.List;

/**
 * (Customer)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-17 09:56:45
 */
public interface CustomerMapper {

    /**
     * 查询所有
     * @param customer
     * @return
     */
    List<Customer> selectList(Customer customer);


    /**
     * 新增接口
     * @param customer
     * @return
     */
    Integer add(Customer customer);

    Integer update(Customer customer);
}

