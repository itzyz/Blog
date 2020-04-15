package cn.itcat.dao;

import cn.itcat.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
   //根据用户名密码查询实现登录功能
   @Select("select * from user where username=#{username} and password=#{password}")
   User findByUsernameAndPassword(String username, String password);
}
