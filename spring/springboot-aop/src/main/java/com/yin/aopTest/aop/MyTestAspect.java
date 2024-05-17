package com.yin.aopTest.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyTestAspect {
    String value1() default ""; // 第一个String类型属性
    String value2() default ""; // 第二个String类型属性
}