package com.cfz.service;

import com.cfz.entity.BusCustomer;
import com.cfz.entity.BusRent;

import java.util.List;

/**
 * (Stat)表服务接口
 *
 * @author makejava
 */
public interface StatService {
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
