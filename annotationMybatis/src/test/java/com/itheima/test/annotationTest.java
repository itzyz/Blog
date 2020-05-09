package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class annotationTest {
    public static void main(String[] args)throws Exception {
        /*读取配置文件*/
        InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
        /*根据字节输入流构建SqlsessionFactory*/
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        /*生产一个session*/
        SqlSession session = factory.openSession();
        /*使用session获取dao代理的对象*/
        UserDao userDao = session.getMapper(UserDao.class);
        /*执行dao*/
        List<User> users = userDao.findAll();
        for (User u:users){
            System.out.println(u);
        }
        /*释放资源*/
        session.close();
        in.close();
    }
}
