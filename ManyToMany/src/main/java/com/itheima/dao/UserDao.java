package com.itheima.dao;

import com.itheima.daomain.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有用户，同时获取到用户下所有账户的信息
     * @return
     */
    List<User> findAll();
    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);
}
