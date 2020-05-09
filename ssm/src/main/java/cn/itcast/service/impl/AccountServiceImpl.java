package cn.itcast.service.impl;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    /*
    * 查询所有
    * */
    public List<Account> findAll() {
        List<Account> accounts=accountDao.findAll();
        return accounts;
    }

    /*
    * 保存用户
    * */
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }
}
