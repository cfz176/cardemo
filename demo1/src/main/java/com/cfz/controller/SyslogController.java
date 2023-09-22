package com.cfz.controller;

import com.alibaba.fastjson.JSONArray;
import com.cfz.annotation.SystemLog;
import com.cfz.entity.Syslog;
import com.cfz.service.SyslogService;
import com.cfz.utils.ExcelUtil;
import com.cfz.utils.PageUtil;
import com.github.pagehelper.Page;
import org.apache.http.HttpResponse;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysLog")
public class SyslogController {

    @Autowired
    private SyslogService syslogService;

    /**
     * 跳转日志列表页面
     * @return
     */
    @SystemLog(methods = "跳转日志列表页面",module = "日志管理模块")
    @RequestMapping("/selectShow")
    public String selectShow() {
        return "/html/sysLog/table.html";
    }

    /**
     * 查询日志列表
     * @param syslog
     * @param page
     * @param limit
     * @return
     */
    @SystemLog(methods = "查询订单列表",module = "日志管理模块")
    @RequestMapping("/selectList")
    @ResponseBody
    public String selectList(Syslog syslog,Integer page,Integer limit) {
        //查询日志
        Page<Syslog> syslogs = syslogService.selectSyslog(syslog, page, limit);
        //设置返回值格式
        PageUtil pageUtil = new PageUtil();
        pageUtil.setCount(syslogs.getTotal()+"");
        pageUtil.setMsg("");
        pageUtil.setData(JSONArray.toJSONString(syslogs.getResult()));
        pageUtil.setCode("0");
        return pageUtil.toString();
    }

    @SystemLog(methods = "导出Excl",module = "日志管理模块")
    @RequestMapping("/selectListExcel")
    public void selectListExcel(HttpServletResponse response, Map map) throws IOException {
        String[] titleName = {"用户名","模块","执行方法","运行时间（ms）","IP地址","访问时间","提交结果"};
        String[] key = {"userid","module","method","response_date","ip","dates","commit"};

        List<Map> list= syslogService.selectListExcel(map);
        ExcelUtil.exportExcel(response,"执行日志",titleName,list,key);
    }
}
