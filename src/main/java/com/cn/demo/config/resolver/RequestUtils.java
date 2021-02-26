package com.cn.demo.config.resolver;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *@Author fengzhilong
 *@Date 2021/1/7 17:05
 *@Desc
 **/
public class RequestUtils {

    public static RequestBodyWrapper toRequestBodyWrapper(HttpServletRequest request){
        // content-type不是json的不处理
        if (request==null || request.getContentType()==null) {
            return null;
        }
        if (request instanceof RequestBodyWrapper) {
            return (RequestBodyWrapper)request;
        }
        if (request instanceof HttpServletRequestWrapper) {
            ServletRequest sr =((HttpServletRequestWrapper) request).getRequest();
            if (sr instanceof RequestBodyWrapper) {
                return (RequestBodyWrapper)sr;
            }
        }
        return null;
    }
}
