package com.yin.aopTest.aop;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.yin.aopTest.pojo.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAdvice {


    @Pointcut("execution(* com.yin.aopTest.controller.AOPTest.aopTest(..))")
    private void pointcut(){}


    @Before("pointcut()")
    public void before(JoinPoint point){
        log.info("前置通知");
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        log.info("后置通知");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        log.info("环绕通知");
        try {
            // 计时
            TimeInterval timer = DateUtil.timer();
            // 执行方法，连接点
            Object result = joinPoint.proceed();
            log.info("处理返回值：{}",result);
            // 查看耗时
            log.info("耗时：{}", timer.interval());
            return result;
        } catch (Throwable throwable) {
            return ResultData.fail("服务器繁忙，请稍后再试");
        }
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint point) {
        Signature signature = point.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        System.out.println("万次执行："+ className+"."+methodName+"---->");

        log.info("返回通知");
    }

    @AfterThrowing(value = "pointcut()", throwing = "t")
    public void afterThrowing(JoinPoint point, Throwable t) {
        log.info("异常通知");
    }

    @Before("@annotation(myTestAspect)")
    public void beforeMyTestAspect(MyTestAspect myTestAspect) {
        String value1 = myTestAspect.value1();
        String value2 = myTestAspect.value2();

        System.out.println("Executing before advice for MyTestAspect annotated methods");
        System.out.println("Value 1: " + value1);
        System.out.println("Value 2: " + value2);
        // 在这里可以添加你的切面逻辑
    }

}