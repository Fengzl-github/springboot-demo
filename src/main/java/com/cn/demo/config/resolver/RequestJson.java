package com.cn.demo.config.resolver;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.ValueConstants;

/**
 *@Author fengzhilong
 *@Date 2021/1/7 17:07
 *@Desc
 **/
public @interface RequestJson {

    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    boolean required() default true;

    String defaultValue() default ValueConstants.DEFAULT_NONE;
}
