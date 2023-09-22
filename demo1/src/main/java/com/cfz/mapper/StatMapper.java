package com.cfz.mapper;

import com.cfz.entity.BusCustomer;
import com.cfz.entity.BusRent;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (BusCustomer)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-28 12:27:18
 */
public interface StatMapper {

    /**
     * 查询用户地区数据
     * @return
     */
    List<BusCustomer> loadCustomerAreaStatJson();

    /**
     * 查询公司年度销售额
     * @return
     */
    List<BusRent> loadCompanyYearGradeStatJson(String year);

    /**
     * 业务员年度业务统计
     * @return
     */
    List<BusCustomer> loadOpernameYearGradeStatJson(String year);
}

