package com.cfz.controller;

import com.cfz.entity.BusCustomer;
import com.cfz.entity.BusRent;
import com.cfz.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stat")
public class StatController {

    @Autowired
    private StatService statService;

    /**
     * 跳转客户地区统计页面
     *
     * @return
     */
    @RequestMapping("/customerAreaStat")
    public String customerAreaStat() {
        return "/html/stat/customerAreaStat.jsp";
    }

    /**
     * 查询客户统计地区信息
     *
     * @return
     */
    @RequestMapping("/loadCustomerAreaStatJson")
    @ResponseBody
    public List<BusCustomer> loadCustomerAreaStatJson() {
        return statService.loadCustomerAreaStatJson();
    }

    /**
     * 跳转年度销售额页面
     *
     * @return
     */
    @RequestMapping("/companyYearGradeStat")
    public String companyYearGradeStat() {
        return "/html/stat/companyYearGradeStat.jsp";
    }

    /**
     * 查询年度销售额
     *
     * @return
     */
    @RequestMapping("/loadCompanyYearGradeStatJson")
    @ResponseBody
    public Double[] loadCompanyYearGradeStatJson(String year) {
        List<BusRent> busRents = statService.loadCompanyYearGradeStatJson(year);
        Double[] price = {0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00};
        busRents.stream()
                .forEach(busRent -> { price[busRent.getMonth() - 1] = busRent.getPrice(); });
        return price;
    }

    /**
     * 跳转年度业务员报表
     *
     * @return
     */
    @RequestMapping("/opernameYearGradeStat")
    public String opernameYearGradeStat() {
        return "/html/stat/opernameYearGradeStat.jsp";
    }

    /**
     * 跳转年度业务员报表
     *
     * @return
     */
    @RequestMapping("/loadOpernameYearGradeStatJson")
    @ResponseBody
    public Map<String, Object> loadOpernameYearGradeStatJson(String year) {
        List<BusCustomer> busCustomers = statService.loadOpernameYearGradeStatJson(year);
        Map<String, Object> hashMap = new HashMap<>();
        List<String> name = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
        busCustomers.stream()
                .forEach(busCustomer -> {
                    name.add( busCustomer.getName());
                    value.add(busCustomer.getValue());
                });
        hashMap.put("name", name);
        hashMap.put("value", value);
        return hashMap;
    }

    /**
     * 跳转年度业务员报表
     *
     * @return
     */
    @RequestMapping("/customerAreaSexStat")
    public String customerAreaSexStat() {
        return "/html/stat/customerAreaSexStat.jsp";
    }
}
