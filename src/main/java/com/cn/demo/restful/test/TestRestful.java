package com.cn.demo.restful.test;

import com.cn.common.exception.FzlException;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import com.cn.demo.dto.TestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author fengzhilong
 *@Date 2021/2/26 14:42
 *@Desc
 **/
@RestController
@Validated
@Slf4j
public class TestRestful {

    @PostMapping("/test")
    public ResResult getTest(@Validated @RequestBody TestDTO testDTO) throws FzlException {

        if (testDTO.getAge() == 24){
            throw new FzlException("年龄不能为24");
        }else {
            return ResCode.OK.msg("成功");
        }
    }
}
