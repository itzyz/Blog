package cn.itcat.service;

import cn.itcat.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface userService {
    /*
    * 查询所有
    * */
    String findAll() throws JsonProcessingException;

    void insertAUser(User user);
}
