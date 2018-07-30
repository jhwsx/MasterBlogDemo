package com.wzc.masterblogdemo.annotation.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这是一个自定义的运行时注解
 * @author wzc
 * @date 2018/7/30
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GetViewTo {
    int value() default -1;
}
