package com.cn.demo.config.error;

import com.alibaba.fastjson.JSONObject;
import com.cn.common.exception.FzlException;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResData;
import com.cn.common.vo.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/2/24 11:32
 *@Desc
 **/
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * @Author fengzhilong
     * @Desc 所有异常报错处理
     * @Date 2021/2/24 11:37
     * @param request
     * @param exception
     * @return com.alibaba.fastjson.JSONObject
     **/
    @ExceptionHandler(value = Exception.class)
    public JSONObject allExceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        exception.printStackTrace();
        JSONObject json = new JSONObject();
        json.put("code", -1);
        json.put("msg", "服务器异常");
        json.put("exception", exception.getMessage());
        return json;
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class, ValidationException.class, BindException.class})
    public ResResult bindExceptionHandler(Exception e) {
        ResResult resResult = ResCode.PARAM_VERIFY_FAILED;
        if (e instanceof MethodArgumentNotValidException) {
            List<FieldError> errorList = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
            resResult = ResCode.PARAM_VERIFY_FAILED.msg(errorList.get(0).getDefaultMessage());
        } else if (e instanceof BindException) {
            List<FieldError> errorList = ((BindException) e).getBindingResult().getFieldErrors();
            resResult = ResCode.PARAM_VERIFY_FAILED.msg(errorList.get(0).getDefaultMessage());
        } else if (e instanceof ValidationException) {
            resResult = ResCode.PARAM_VERIFY_FAILED.msg(e.getMessage());
        }
        log.warn("[参数校验异常] ##  response -> {}", JSONObject.toJSONString(resResult));
        return resResult;
    }


    @ExceptionHandler(value = {FzlException.class})
    public ResResult saasExceptionHandler(FzlException e) {
        int errCode = e.getCode();
        ResResult resResult = new ResData(errCode == 0 ? -1 : errCode, e.getMessage(), null);
        log.error("[FzlException] ## 业务异常 response -> {}", JSONObject.toJSONString(resResult));
        return resResult;
    }
}
