package com.cfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Syslog)实体类
 *
 * @author makejava
 * @since 2023-07-27 09:59:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Syslog implements Serializable {
    private static final long serialVersionUID = -36094450981690814L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 用户
     */
    private String userid;
    /**
     * 模块
     */
    private String module;
    /**
     * 方法
     */
    private String method;
    /**
     * 执行时间
     */
    private String responseDate;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 访问时间
     */
    private String dates;
    /**
     * 提交结果
     */
    private String commit;

    private String startTime;

    private String endTime;


}

