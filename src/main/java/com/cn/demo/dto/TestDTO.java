package com.cn.demo.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *@Author fengzhilong
 *@Date 2021/2/26 14:43
 *@Desc
 **/
@Data
public class TestDTO {

    @NotBlank(message = "工号不能为空")
    private String ghid;
    @NotBlank(message = "密码不能为空")
    private String pass;
    @Min(value = 6, message = "年龄不能小于六岁")
    @Max(value = 150, message = "年龄不能大于150")
    @NotNull(message = "年龄不能为空")
    private Integer age;

}
