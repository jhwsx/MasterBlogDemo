package com.wzc.masterblogdemo.reflection.part1;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.csdn.net/harvic880925/article/details/50072739
 * @author wzc
 * @date 2019/3/21
 */
public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.setName("cat");

        Class class1 = Animal.class;
        System.out.println(class1.getName());

        Class<?> class2 = Animal.class;
        System.out.println(class2.getName());

        Class<Animal> class3 = Animal.class;
        System.out.println(class3.getName());

        // 获取类类型
        Person person = new Person();

        Class a = person.getClass();

        Class b = Person.class;

        try {
            Class c = Class.forName("com.wzc.masterblogdemo.reflection.part1.Person");

            Class d = animal.getClass().getClassLoader().loadClass("com.wzc.masterblogdemo.reflection.part1.Person");

            System.out.println(a == b && a == c && a == d);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 1，类名，包名获取
        Class animalClass = Animal.class;
        System.out.println("完整类名："+ animalClass.getName());
        System.out.println("完整类名："+ animalClass.getCanonicalName());
        System.out.println("仅获取类名："+ animalClass.getSimpleName());
        System.out.println("获取包名："+ animalClass.getPackage().getName());
        // 2，获取超类 Class 对象
        try {
            Class<?> animalImplClass = Class.forName("com.wzc.masterblogdemo.reflection.part1.AnimalImpl");
            Class<?> superclass = animalImplClass.getSuperclass();
            System.out.println("超类："+superclass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 3. 获取类所直接继承的接口的Class对象
        Class[] animalClassInterfaces = animalClass.getInterfaces();
        for (Class classInterface : animalClassInterfaces) {
            System.out.println("Animal 直接继承的接口：" + classInterface.getName());
        }
        try {
            Class<?> animalImplClass1 = Class.forName("com.wzc.masterblogdemo.reflection.part1.AnimalImpl");
            Class<?>[] interfaces = animalImplClass1.getInterfaces();
            if (interfaces.length > 0) {
                for (Class<?> anInterface : interfaces) {
                    System.out.println("AnimalImpl 直接继承的接口：" + anInterface.getName());
                }
            } else {
                System.out.println("AnimalImpl 直接继承的接口：无");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 4, 获取所有接口
//        getAllInterfaces(AnimalImpl2.class);
//        for (Class aClass : sClassList) {
//            System.out.println(aClass.getName());
//        }

        Class[] allInterfaces2 = getAllInterfaces2(AnimalImpl2.class);
        for (Class aClass : allInterfaces2) {
            System.out.println(aClass);
        }

        // 5, 获取类的访问修饰符
        try {
            Class<?> aClass = a.getClassLoader().loadClass(InnerClass.class.getName());
            int modifiers = aClass.getModifiers();
            String s = Modifier.toString(modifiers);
            System.out.println("InnerClass 的访问修饰符："+s);
            boolean aFinal = Modifier.isFinal(modifiers);
            System.out.println("is final=" +aFinal);
            boolean aPublic = Modifier.isPublic(modifiers);
            System.out.println("is public=" +aPublic);
            boolean aStatic = Modifier.isStatic(modifiers);
            System.out.println("is static=" +aStatic);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Class> sClassList = new ArrayList<>();
    public static void getAllInterfaces(Class clazz) {
        Class[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            for (Class anInterface : interfaces) {
                sClassList.add(anInterface);
            }
        }
        Class superclass = clazz.getSuperclass();
        if (superclass != Object.class) {
            getAllInterfaces(superclass);
        }

    }

    public static Class[] getAllInterfaces2(Class clazz) {
        // 先获取本类的直接接口
        Class[] interfacesSelf = clazz.getInterfaces();
        // 再获取父类的直接接口
        Class[] interfacesSuper = null;
        Class superclass = clazz.getSuperclass();
        if (superclass != null) {
            interfacesSuper = getAllInterfaces2(superclass);
        }
        if (interfacesSelf == null && interfacesSuper == null) {
            return null;
        } else if (interfacesSelf == null && interfacesSuper != null) {
            return interfacesSuper;
        } else if (interfacesSelf != null && interfacesSuper == null) {
            return interfacesSelf;
        } else {
            Class[] result = new Class[interfacesSelf.length + interfacesSuper.length];
            System.arraycopy(interfacesSelf, 0, result, 0, interfacesSelf.length);
            System.arraycopy(interfacesSuper, 0, result, interfacesSelf.length, interfacesSuper.length);
            return result;
        }
    }

    public static final class InnerClass {

    }


}
