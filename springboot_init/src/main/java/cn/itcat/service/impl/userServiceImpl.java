package cn.itcat.service.impl;

import cn.itcat.dao.UserDao;
import cn.itcat.domain.User;
import cn.itcat.service.userService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class userServiceImpl implements userService {

    @Resource
    private UserDao userDao;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    public String findAll() throws JsonProcessingException {
        //从缓存中拿数据
        String usersData = redisTemplate.boundValueOps("userDao.findAll").get();
        //判断值是否为空
        if(usersData==null){
            //从数据库中取数据
            List<User> users=userDao.findAll();
            //转为json串
            ObjectMapper obj=new ObjectMapper();
            usersData =obj.writeValueAsString(users);
            //将数据存储到redis中，下次在查询直接从redis中获得数据，不用在查询数据库
            redisTemplate.boundValueOps("userDao.findAll").set(usersData);
            System.out.println("从数据库中查询数据");
        }else {
            System.out.println("从Redis中查询数据");
        }
        return usersData;
    }

    public void insertAUser(User user) {
        userDao.insertAUser(user);
    }
}
