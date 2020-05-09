package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
/*
* 注解一共有四个
* @Select@Insert@Update@Delete
* */
@CacheNamespace(blocking = true)
public interface UserDao {
    /*
        查询所有用户
        annotation配置别名
     */
    @Select("select * from user")
    @Results(id = "userMap",value = {
            @Result(id = true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "id",property = "account",
                    many = @Many(select = "com.itheima.dao.AccountDao.findByAccountId"
                            ,fetchType = FetchType.LAZY))//一个用户拥有多个账号
    })//查询所有 包括拥有的账号
    List<User> findAll();
    /*
    * 查询单个
    * */
    @Select("select * from user where id=#{id}")
    @ResultMap(value = {"userMap"})
    User findByIdUser(Integer id);
    /*
    * 添加用户
    * */
    @Insert("insert into user(username,birthday,sex,address) values(#{username},#{birthday},#{sex},#{address})")
    void save(User user);

    /*
	
    * 修改操作
    * */
    @Update("update user set username=#{username},sex=#{sex} where id=#{id}")
    void update(User user);
}
