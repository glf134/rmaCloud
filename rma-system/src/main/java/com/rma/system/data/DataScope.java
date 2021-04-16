package com.rma.system.data;

import java.lang.annotation.*;

/**
 * 数据权限自定义注解
 * @author glf
 * @since 2020-05-07
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataScope {

    // 权限的类型
    String type() default "";

    // 限制的字段
    String column() default "dept_id";

}