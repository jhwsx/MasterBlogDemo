package com.wzc.masterblogdemo.reflection.part2;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * @author wzc
 * @date 2019/3/22
 */
public class Main2 {
    public static void main(String[] args) {
        // 3, GenericArrayType
        Class<?> klazz = PointArrayImpl.class;
        Type[] genericInterfaces = klazz.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            System.out.println(genericInterface);
            if (genericInterface instanceof ParameterizedType) {
                ParameterizedType anInterface = (ParameterizedType) genericInterface;
                // 获取表示此类型实际类型参数的 Type 对象数组
                Type[] actualTypeArguments = anInterface.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    if (actualTypeArgument instanceof GenericArrayType) {
                        GenericArrayType typeArgument = (GenericArrayType) actualTypeArgument;
                        Type genericComponentType = typeArgument.getGenericComponentType();
                        if (genericComponentType instanceof TypeVariable) {
                            TypeVariable componentType = (TypeVariable) genericComponentType;
                            System.out.println("PointArrayImpl 填充父类接口的泛型参数类型为：" + componentType.getName());
                        }


                    }
                }
            }
        }
    }
}
