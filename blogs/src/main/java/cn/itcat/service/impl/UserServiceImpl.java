package cn.itcat.service.impl;

import cn.itcat.dao.UserMapper;
import cn.itcat.entity.User;
import cn.itcat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional/*开启事务*/
public class UserServiceImpl implements UserService {
    /*注入mapper*/
    @Autowired
    private UserMapper userMapper;
    /*登录*/
    public User checkUser(String username, String password) {
        User user=userMapper.findByUsernameAndPassword(username,password);
        return user;
    }
}
