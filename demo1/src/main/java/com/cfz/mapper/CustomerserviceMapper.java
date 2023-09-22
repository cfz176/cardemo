package com.cfz.mapper;

import com.cfz.entity.Customerservice;

/**
 * (Customerservice)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-14 13:24:25
 */
public interface CustomerserviceMapper {

    /**
     * 登陆查询
     * @param customer
     * @return
     */
    Customerservice login(Customerservice customer);

//    /**
//     * 通过ID查询单条数据
//     *
//     * @param id 主键
//     * @return 实例对象
//     */
//    Customer queryById(Integer id);
//
//    /**
//     * 统计总行数
//     *
//     * @param customerservice 查询条件
//     * @return 总行数
//     */
//    long count(Customer customerservice);
//
//    /**
//     * 新增数据
//     *
//     * @param customerservice 实例对象
//     * @return 影响行数
//     */
//    int insert(Customer customerservice);
//
//    /**
//     * 批量新增数据（MyBatis原生foreach方法）
//     *
//     * @param entities List<Customerservice> 实例对象列表
//     * @return 影响行数
//     */
//    int insertBatch(@Param("entities") List<Customer> entities);
//
//    /**
//     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
//     *
//     * @param entities List<Customerservice> 实例对象列表
//     * @return 影响行数
//     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
//     */
//    int insertOrUpdateBatch(@Param("entities") List<Customer> entities);
//
//    /**
//     * 修改数据
//     *
//     * @param customerservice 实例对象
//     * @return 影响行数
//     */
//    int update(Customer customerservice);
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param id 主键
//     * @return 影响行数
//     */
//    int deleteById(Integer id);

}

