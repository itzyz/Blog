package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * 账户的持久层实现类
 */
@Repository(value ="accountDao" )//Repository用于把类放入spring容器中
public class AccountDaoImpl implements IAccountDao {
    public  void saveAccount(){
        System.out.println("保存了账户");
    }
}
