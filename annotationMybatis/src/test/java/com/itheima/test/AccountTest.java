package com.itheima.test;

import com.itheima.dao.AccountDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.Account;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private SqlSessionFactory factory;
    private SqlSession session;
    private InputStream in;
    private AccountDao accountDao;

    @Before
    public void init()throws Exception{
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        factory=new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(AccountDao.class);
    }
    @After
    public void dectory()throws Exception{
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void findAll(){
        List<Account> account=accountDao.findAll();
        for (Account a:account){
            System.out.println("----------每个账户全部信息------------");
            System.out.println(a);
            System.out.println(a.getUser());
        }
    }
}
