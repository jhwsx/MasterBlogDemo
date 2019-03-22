package com.wzc.masterblogdemo.reflection.part2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * https://blog.csdn.net/harvic880925/article/details/50085595
 *
 * @author wzc
 * @date 2019/3/21
 */
public class Main {
    public static void main(String[] args) {
        // 一、获取泛型超类和接口的相关信息
        // 1, 获取泛型超类的相关信息
        Class<?> clazz = PointImpl.class;
        Type type = clazz.getGenericSuperclass();
        System.out.println(type.toString());
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            // 返回表示此类型实际类型参数的 Type 对象数组
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                Class actualTypeArgumentClass = (Class) actualTypeArgument;
                System.out.println("PointImpl 填充父类的泛型参数类型为：" + actualTypeArgumentClass.getName());
            }
            // 返回表示声明此类型的类或接口的 Type 对象
            Type rawType = parameterizedType.getRawType();
            Class klazz = (Class) rawType;
            System.out.println("PointImpl 的父类类型为：" + klazz.getName());

        }
        // 2, 获取继承的泛型接口的相关信息
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        if (genericInterfaces.length > 0) {
            for (Type genericInterface : genericInterfaces) {
                System.out.println(genericInterface);
                if (genericInterface instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
                    // 返回表示此类型实际类型参数的 Type 对象数组
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    for (Type actualTypeArgument : actualTypeArguments) {
                        Class typeArgument = (Class) actualTypeArgument;
                        System.out.println("PointImpl 填充接口的泛型参数类型为：" + typeArgument.getName());
                    }
                    // 返回表示声明此类型的类或接口的 Type 对象
                    Type rawType = parameterizedType.getRawType();
                    Class klazz = (Class) rawType;
                    System.out.println("PointImpl 的父类接口类型为：" + klazz.getName());
                }
            }
        }

        // 3, TypeVariable
        Class<?> klazz = PointGenericityImpl.class;
        Type[] genericInterfaces1 = klazz.getGenericInterfaces();
        for (Type type1 : genericInterfaces1) {
            System.out.println(type1);
            if (type1 instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type1;
                // 获取表示此类型实际类型参数的 Type 对象数组
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    if (actualTypeArgument instanceof TypeVariable) {
                        TypeVariable typeArgument = (TypeVariable) actualTypeArgument;
                        System.out.println("PointGenericityImpl 填充接口的泛型参数类型为：" + typeArgument.getName());
                        // 返回表示此类型变量上边界的 Type 对象的数组
                        Type[] bounds = typeArgument.getBounds();
                        for (Type bound : bounds) {
                            Class<?> boundClass = (Class<?>) bound;
                            System.out.println("bound为：" + boundClass.getName());
                        }
                    }
                    if (actualTypeArgument instanceof Class) {
                        Class typeArgument = (Class) actualTypeArgument;
                        System.out.println("PointGenericityImpl 填充接口的泛型参数类型为：" + typeArgument.getName());
                    }

                }
                // 返回表示声明此类型的类或者接口的 Type 对象
                Type rawType = parameterizedType.getRawType();
                Class klarz = (Class) rawType;
                System.out.println("PointGenericityImpl 的父类接口类型为：" + klarz.getName());
            }

        }
    }
}
