package org.example.testReflect;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Author: yin7331
 * Date: 2023/10/21 16:45
 * Describe:
 */
@Slf4j
public class TestReflect {
    public static void main(String[] args) {
        try {

            // 通过反射获取类的Class
            //--->查看JDK帮助文档
            Class<?> c1 = Class.forName("org.example.pojo.User");
            // 一个类被加载后 , 类的整个结构信息会被放到对应的Class对象中
            System.out.println(c1);
            // 一个类只对应一个Class对象
            Class<?> c2 = Class.forName("org.example.pojo.User");
            System.out.println(c1.hashCode());
            System.out.println(c2.hashCode());

            User user = new User();
            System.out.println("这个人是:"+user.getName());
            //获得class办法一:通过对象获得
            Class clazz1 = user.getClass();
            //获得class办法二:通过字符串获得(包名+类名)
            Class clazz2 = Class.forName("org.example.pojo.User");
            //获得class办法三:通过类的静态成员class获得
            Class clazz3 = User.class;
            //获得class办法四:只针对内置的基本数据类型
            Class clazz4 = Integer.TYPE;
            //获得父类类型
            Class clazz5 = clazz2.getSuperclass();
            System.out.println("clazz1::"+clazz1);
            System.out.println("clazz2::"+clazz2);
            System.out.println("clazz3::"+clazz3);
            System.out.println("clazz4::"+clazz4);
            System.out.println("clazz5::"+clazz5);
            // 获取类的名称
            String className = clazz2.getName();
            System.out.println("className = " + className);

            // 获取类的包名
            Package pkg = clazz2.getPackage();
            String packageName = pkg.getName();
            System.out.println("packageName = " + packageName);

            // 使用 Class 对象实例化对象
            User obj = (User) clazz2.newInstance();
            System.out.println("obj.getName() = " + obj.getName());
            // 获取所有字段
            Field[] fields = clazz2.getDeclaredFields();
            Arrays.stream(fields)
                    .collect(Collectors.toList())
                    .forEach(System.out::println);


            // 获取字段的值
            Field field = clazz2.getDeclaredField("id");
            field.setAccessible(true); // 如果字段是私有的，需要设置为可访问
            Integer value = (Integer) field.get(obj);
            System.out.println("value = " + value);

            // 获取所有方法
            Method[] declaredMethods = clazz2.getDeclaredMethods();
            for (Method method:declaredMethods
                 ) {
                System.out.println("method = " + method);
            }

            // 获取方法
            Method method = clazz2.getDeclaredMethod("soutMessage", String.class);
            method.setAccessible(true); // 如果方法是私有的，需要设置为可访问

            // 调用方法
            Object result = method.invoke(obj, "hello");
            System.out.println("result = " + result);

        } catch (Exception e) {
            log.debug(":::::"+e);
        } finally {

        }

    }
}
