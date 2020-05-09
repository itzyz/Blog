package cn.itcast.dao;

import cn.itcast.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountDao {
    /*
    * 查询所有
    * */

    @Select("select * from account")
    List<Account> findAll();

    /*
    * 保存用户
    * */
    @Insert("insert into account(name,money) values(#{name},#{money})")
    void saveAccount(Account account);
}
