package com.itheima.test;

import com.itheima.dao.AccountDao;
import com.itheima.dao.UserDao;
import com.itheima.daomain.Account;
import com.itheima.daomain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * mybatis的入门案例
 */
public class Mytest {

    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args)throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
         UserDao userDao = session.getMapper(UserDao.class);
        AccountDao accountDao=session.getMapper(AccountDao.class);

        //5.使用代理对象执行方法
        User byId = userDao.findById(41);
        System.out.println(byId);
        List<User> users = userDao.findAll();
        /*List<Account> accounts=accountDao.findAll();*/
      /*  for(User user : users){
            System.out.println(user);
        }
        *//*List<User> user2=userDao.findById(41);*//*

       for(Account account : accountDao.findAll()){
            System.out.println(account);
            System.out.println(account.getUser().getAddress()+""+account.getUser().getUsername());
        }*/
        //6.释放资源
        session.close();
        in.close();
    }
}
