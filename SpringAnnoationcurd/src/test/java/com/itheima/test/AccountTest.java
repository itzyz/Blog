package com.itheima.test;

import com.itheima.config.SpringConfigruration;
import com.itheima.config.jdbcConfig;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)/*Spring集成Junit*/
@ContextConfiguration(classes = SpringConfigruration.class)/*读取配置类*/
public class AccountTest {
    @Autowired
    private AccountService accountService;
    @Test
    public void findAll(){
        //调用方法
        List<Account> accounts = accountService.findAll();
        for (Account account:accounts){
            System.out.println(account);
        }
    }
    @Test
    public void findById(){
        //调用方法
        Account account = accountService.findById(4);
        System.out.println(account);
    }
    @Test
    public void Save(){
        //调用方法
        Account account=new Account();
        account.setMoney(920.2);
        account.setName("张三丰");
        accountService.SaveAccount(account);
    }
    @Test
    public void Update(){
        //调用方法
        Account account = accountService.findById(4);
        account.setMoney(500.0);
        account.setName("张无忌");
        accountService.UpdateAccount(account);
    }
    @Test
    public void Delete(){
        //调用方法
        accountService.DeleteAccount(7);
    }
}
