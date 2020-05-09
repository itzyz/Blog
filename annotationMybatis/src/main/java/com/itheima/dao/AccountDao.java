package com.itheima.dao;

import com.itheima.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {
    /*
    * 查询所有包括账户拥有者
    * */
    @Select("select * from account")
    @Results(id="accountMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            @Result(column = "uid" ,property = "user",
                    one = @One(select = "com.itheima.dao.UserDao.findByIdUser"
                            ,fetchType = FetchType.EAGER))//EAGER立即加载，lazy延迟加载
    })
    List<Account> findAll();
    /*
    * 通过用户id查询账号
    * */
    @Select("select * from account where uid=#{uid}")
    @ResultMap(value = {"accountMap"})
    Account findByAccountId(Integer uid);
}
