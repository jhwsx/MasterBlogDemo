package com.wzc.masterblogdemo.annotation.inherited_demo;

import java.util.Arrays;

/**
 * http://latty.iteye.com/blog/2371766
 * 这是为了演示@Inherited注解的demo
 * 把 ATable 注解里的 @Inherited 注释 和 不注释 分别查看结果.
 */
public class TestMain {
    public static void main(String[] args) {

        Class<Sub> clazz = Sub.class;

        System.out.println("============================Field===========================");
        System.out.println(Arrays.toString(clazz.getFields()));
        System.out.println(Arrays.toString(clazz.getDeclaredFields()));  //all + 自身    
        System.out.println("============================Method===========================");
        System.out.println(Arrays.toString(clazz.getMethods()));   //public + 继承    
        //all + 自身    
        System.out.println(Arrays.toString(clazz.getDeclaredMethods()));
        System.out.println("============================Constructor===========================");
        System.out.println(Arrays.toString(clazz.getConstructors()));
        System.out.println(Arrays.toString(clazz.getDeclaredConstructors()));
        System.out.println("============================AnnotatedElement===========================");
        //注解DBTable2是否存在于元素上    
        System.out.println(clazz.isAnnotationPresent(BTable.class));
        //如果存在该元素的指定类型的注释DBTable2，则返回这些注释，否则返回 null。    
        System.out.println(clazz.getAnnotation(BTable.class));
        //继承    
        System.out.println(Arrays.toString(clazz.getAnnotations())); // 可以打印出当前类的注解和父类的注解
        System.out.println(Arrays.toString(clazz.getDeclaredAnnotations()));  // 只会打印出当前类的注解
    }
} 