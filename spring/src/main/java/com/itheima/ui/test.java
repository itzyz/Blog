package com.itheima.ui;

import com.itheima.service.Iservice;
import com.itheima.serviceImpl.serviceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        Iservice iservice=(Iservice) ac.getBean("Iservice");
        iservice.UserSave();
    }
}
