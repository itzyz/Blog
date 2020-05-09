package com.itheima.service;

import com.itheima.domain.Account;

import java.util.List;

public interface AccountService {
    /*查询所有*/
    List<Account> findAll();
    /*根据ID查询*/
    Account findById(Integer id);
    /*添加*/
    void SaveAccount(Account account);
    /*更新*/
    void UpdateAccount(Account account);
    /*删除*/
    void DeleteAccount(Integer id);
}
