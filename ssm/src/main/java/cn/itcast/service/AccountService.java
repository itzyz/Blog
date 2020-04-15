package cn.itcast.service;

import cn.itcast.domain.Account;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    /*
     * 查询所有
     * */
    List<Account> findAll();

    /*
     * 保存用户
     * */
    void saveAccount(Account account);
}
