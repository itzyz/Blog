package cn.itcat.service;

import cn.itcat.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    /*检查用户是否存在*/
    User checkUser(String username,String password);
}
