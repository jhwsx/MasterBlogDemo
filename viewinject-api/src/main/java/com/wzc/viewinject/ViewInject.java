package com.wzc.viewinject;

/**
 * @author wzc
 * @date 2018/7/30
 */
public interface ViewInject<T> {
    void inject(T t, Object source);
}
