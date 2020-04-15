package com.itheima.service.Impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.Impl.AccountDaoImpl;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

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
