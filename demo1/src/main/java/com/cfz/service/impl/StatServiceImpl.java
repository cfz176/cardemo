package com.cfz.service.impl;

import com.cfz.entity.BusCustomer;
import com.cfz.entity.BusRent;
import com.cfz.mapper.StatMapper;
import com.cfz.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Stat)表服务接口
 *
 * @author makejava
 */
@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatMapper busCustomerMapper;

    @Override
    public List<BusCustomer> loadCustomerAreaStatJson() {
        return busCustomerMapper.loadCustomerAreaStatJson();
    }

    @Override
    public List<BusRent> loadCompanyYearGradeStatJson(String year) {
        return busCustomerMapper.loadCompanyYearGradeStatJson(year);
    }

    @Override
    public List<BusCustomer> loadOpernameYearGradeStatJson(String year) {
        return busCustomerMapper.loadOpernameYearGradeStatJson(year);
    }

}
