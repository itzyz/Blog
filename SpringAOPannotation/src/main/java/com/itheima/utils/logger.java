package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component("logger")
@ComponentScan(basePackages = "com.itheima")
@EnableAspectJAutoProxy
@Aspect
public class logger {
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pointCut() {
    }

    /*配置前置通知*/
    @Before("pointCut()")
    public void beforLogger() {

        System.out.println("日志记录启动成功....");
    }

    /*后置通知*/
    @AfterReturning("pointCut()")
    public void afterRunningLogger() {

        System.out.println("日志记录开始");
    }

    /*异常通知*/
    @AfterThrowing("pointCut()")
    public void thorwingLogger() {

        System.out.println("日志记录异常");
    }

    /*最终通知*/
    @After("pointCut()")
    public void afterLogger() {
        System.out.println("日志记录结束..");
    }

    /*环绕通知*/
    @Around("pointCut()")
    public Object aroundLogger(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            Object[] args = pjp.getArgs();/*得到方法参数*/
            System.out.println("前置");
            rtValue = pjp.proceed(args);/*执行方法*/
            System.out.println("后置");
            return rtValue;
        } catch (Throwable e) {
            new Exception(e + "异常");
        } finally {
            System.out.println("最终");
        }
        return 0;
    }
}
