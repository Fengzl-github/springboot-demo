package com.cn.demo.config.resolver;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 转换封装json文本成为JsonObject对象，方便后面使用
 * Created by yml on 2016/11/1.
 */
@WebFilter(urlPatterns = "/*", filterName = "RequestWrapperFilter")
public class RequestWrapperFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest resquest = (HttpServletRequest) servletRequest;
        try {
            if (servletRequest.getContentType() != null
                    && (servletRequest.getContentType().toLowerCase().contains("json")
//                       ||
//                       servletRequest.getContentType().toLowerCase().contains("xml")
            )) {
                RequestBodyWrapper myRequestWrapper = new RequestBodyWrapper((HttpServletRequest) servletRequest);
                filterChain.doFilter(myRequestWrapper, servletResponse);
            } else {
//                System.out.println("JsonFilter ContentType: " + servletRequest.getContentType());
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
