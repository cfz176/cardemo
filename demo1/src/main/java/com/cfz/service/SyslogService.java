package com.cfz.service;

import com.cfz.entity.BusCustomer;
import com.cfz.entity.Syslog;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * (Syslog)表服务接口
 *
 * @author makejava
 * @since 2023-07-27 09:59:03
 */
public interface SyslogService {

    /**
     * 查询日志信息
     * @param syslog
     * @return
     */
    Page<Syslog> selectSyslog(Syslog syslog, Integer page, Integer limit);

    /**
     * 添加日志信息
     * @param syslog
     * @return
     */
    Integer saveLog(Syslog syslog);

    /**
     * 查询日志信息生成 Excel
     * @param map
     * @return
     */
    List<Map> selectListExcel(Map map);


}
