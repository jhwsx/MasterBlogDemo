package com.wzc.masterblogdemo.dagger2.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * 自定义注解类
 * @author wzc
 * @date 2018/6/2
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
