package com.wzc.masterblogdemo.reflection.part2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * @author wzc
 * @date 2019/3/22
 */
public class Main3 {
    public static void main(String[] args) {
        // 4, WildcardType
        Class<PointWildImpl> pointWildClass = PointWildImpl.class;
        Type[] genericInterfaces = pointWildClass.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            System.out.println(genericInterface.toString());
            if (genericInterface instanceof ParameterizedType) {
                ParameterizedType anInterface = (ParameterizedType) genericInterface;
                // 获取表示此类型实际类型参数的 Type 对象数组, Comparable<? extends Number>
                Type[] actualTypeArguments = anInterface.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    if (actualTypeArgument instanceof ParameterizedType) {
                        ParameterizedType typeArgument = (ParameterizedType) actualTypeArgument;
                        // 获取表示此类型实际类型参数的 Type 对象数组, ? extends Number
                        Type[] actualTypeArguments1 = typeArgument.getActualTypeArguments();
                        for (Type type : actualTypeArguments1) {
                            if (type instanceof WildcardType) {
                                WildcardType wildcardType = (WildcardType) type;
                                Type[] lowerBounds = wildcardType.getLowerBounds();
                                for (Type lowerBound : lowerBounds) {
                                    Class bound = (Class) lowerBound;
                                    System.out.println("lower bound : " + bound.getName());
                                }
                                Type[] upperBounds = wildcardType.getUpperBounds();
                                for (Type upperBound : upperBounds) {
                                    Class bound = (Class) upperBound;
                                    System.out.println("upper bound: " + bound.getName());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
