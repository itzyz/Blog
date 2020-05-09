package com.itheima.service.impl;

import com.itheima.service.AccountService;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    public void saveAccount() {
        System.out.println("保存成功！");
    }

    public void updateAccount(int i) {
        System.out.println("更新成功！");
    }

    public int deleteAccount() {
        System.out.println("删除成功！");
        return 0;
    }
}
