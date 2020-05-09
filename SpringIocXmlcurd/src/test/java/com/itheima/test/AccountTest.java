package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AccountTest {
    @Test
    public void findAll(){
        //读取配置文件
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        //获得bean
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        //调用方法
        List<Account> accounts = accountService.findAll();
        for (Account account:accounts){
            System.out.println(account);
        }
    }
    @Test
    public void findById(){
        //读取配置文件
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        //获得bean
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        //调用方法
        Account account = accountService.findById(2);
        System.out.println(account);
    }
    @Test
    public void Save(){
        //读取配置文件
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        //获得bean
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        //调用方法
        Account account=new Account();
        account.setMoney(920.2);
        account.setName("张三丰");
        accountService.SaveAccount(account);
    }
    @Test
    public void Update(){
        //读取配置文件
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        //获得bean
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        //调用方法
        Account account = accountService.findById(4);
        account.setMoney(500.0);
        account.setName("张无忌");
        accountService.UpdateAccount(account);
    }
    @Test
    public void Delete(){
        //读取配置文件
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        //获得bean
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        //调用方法
        accountService.DeleteAccount(7);
    }
}
