package com.cfz.entity.vo;

import com.cfz.entity.MasterAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MasterVo {
    /**
     * 工程师编号(自增)
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private String age;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 所属部门编号
     */
    private String did;

    private String phone;
    /**
     * 是否删除 默认为0
     */
    private String del;

    /**
     * 地址
     */
    private String address;

    private MasterAddress masterAddress;

}
