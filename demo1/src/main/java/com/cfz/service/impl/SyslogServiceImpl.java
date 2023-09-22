package com.cfz.service.impl;

import com.cfz.entity.BusCustomer;
import com.cfz.entity.Syslog;
import com.cfz.mapper.SyslogMapper;
import com.cfz.service.SyslogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Syslog)表服务实现类
 *
 * @author makejava
 * @since 2023-07-27 09:59:03
 */
@Service("syslogService")
public class SyslogServiceImpl implements SyslogService {

    @Autowired
    private SyslogMapper syslogMapper;

    /**
     * 查询日志信息
     *
     * @param syslog
     * @return
     */
    @Override
    public Page<Syslog> selectSyslog(Syslog syslog, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        Page<Syslog> syslogs = (Page<Syslog>) syslogMapper.selectSyslog(syslog);
        return syslogs;
    }

    /**
     * 添加日志信息
     *
     * @param syslog
     * @return
     */
    @Override
    public Integer saveLog(Syslog syslog) {
        return syslogMapper.saveLog(syslog);
    }

    /**
     * 导出Excel
     * @param map
     * @return
     */
    @Override
    public List<Map> selectListExcel(Map map) {
        return syslogMapper.selectListExcel(map);
    }

}
