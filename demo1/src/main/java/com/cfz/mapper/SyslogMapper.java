package com.cfz.mapper;

import com.cfz.entity.Syslog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

/**
 * (Syslog)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-27 09:59:02
 */
public interface SyslogMapper {

    /**
     * 查询日志信息
     * @param syslog
     * @return
     */
    List<Syslog> selectSyslog(Syslog syslog);

    /**
     * 添加日志信息
     * @param syslog
     * @return
     */
    Integer saveLog(Syslog syslog);

    /**
     * 导出Excel
     * @param map
     * @return
     */
    List<Map> selectListExcel(Map map);
}

