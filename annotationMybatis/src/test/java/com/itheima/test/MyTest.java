package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyTest {
    private SqlSessionFactory factory;
    private SqlSession session;
    private InputStream in;
    private UserDao userDao;

    @Before
    public void init()throws Exception{
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        factory=new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }
    @After
    public void dectory()throws Exception{
       /* session.commit();
          session.close();*/
        in.close();
    }
    @Test
    public void findAll(){
        List<User> user=userDao.findAll();
        for (User u:user){
            System.out.println("---------每个用户拥有的账号------------");
            System.out.println(u);
            System.out.println(u.getAccount());
        }
    }
    @Test
    public void findById(){
        SqlSession session = factory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.findByIdUser(49);
        System.out.println(user);

        session.close();//释放一级缓存

        SqlSession session1 = factory.openSession();//再次打开session
        UserDao userDao1 = session1.getMapper(UserDao.class);
        User user1 = userDao1.findByIdUser(49);
        System.out.println(user1);
        session1.close();
     /*   User user = userDao.findByIdUser(45);
        System.out.println(user);
        session.close();

        SqlSession session1=factory.openSession();
        UserDao userDao1 = session1.getMapper(UserDao.class);
        User user1 = userDao1.findByIdUser(45);
        System.out.println(user1);
        session1.close();*/
    }
    @Test
    public void Save(){
        User user=new User();
        user.setUserName("Annotation Test");
        user.setUserSex("男");
        user.setUserAddress("河南省南阳市");
        user.setUserBirthday(new Date());
        userDao.save(user);
    }

    @Test
    public void Update(){
        User user=new User();
        user.setUserId(43);
        user.setUserName("Rose");
        user.setUserSex("女");
        userDao.update(user);
    }


}
