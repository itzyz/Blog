package com.itheima.service.Impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.Impl.AccountDaoImpl;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public void setAccountDao(AccountDaoImpl accountDao) {
        this.accountDao=accountDao;
    }
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    public void SaveAccount(Account account) {
        accountDao.SaveAccount(account);
    }

    public void UpdateAccount(Account account) {
        accountDao.UpdateAccount(account);
    }

    public void DeleteAccount(Integer id) {
        accountDao.DeleteAccount(id);
    }

}
