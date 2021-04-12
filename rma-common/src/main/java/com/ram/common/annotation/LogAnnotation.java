package com.ram.common.annotation;

import java.lang.annotation.*;

/**
 * @author glf
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    //模块名
    String module();
    //是否记录请求日志
    boolean recordRequestParam() default false;
}
