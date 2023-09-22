package com.cfz.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    /**
     * 客户id
     */
    private Integer id;
    /**
     * 客户姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;
    /**
     * 身份证
     */
    @NotBlank(message = "身份证不能为空")
    private String idcard;
    /**
     * 车牌号
     */
    @NotBlank(message = "姓名不能为空")
    private String car;
    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空")
    private String sex;
    /**
     * 是否显示 0显示 1不显示
     */
    private String del;
}
