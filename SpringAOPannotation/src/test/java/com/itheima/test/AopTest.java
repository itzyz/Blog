package com.itheima.test;

import com.itheima.service.AccountService;
import com.itheima.utils.logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
    public static void main(String[] args) {
        ApplicationContext ac=new AnnotationConfigApplicationContext(logger.class);
        AccountService as=(AccountService)ac.getBean("accountService");
        as.saveAccount();
    }
}
