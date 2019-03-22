package com.wzc.masterblogdemo.reflection.part2;

import java.io.Serializable;

/**
 * @author wzc
 * @date 2019/3/22
 */
public class PointGenericityImpl<T extends Number& Serializable> implements PointInterface<T,Integer> {
}
