package cn.itcat.contorlle;

import cn.itcat.domain.User;
import cn.itcat.service.userService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserContorller {
    @Autowired
    @Qualifier("userServiceImpl")
    private userService service;

    @RequestMapping("/user")
    @ResponseBody
    public String getUser() throws JsonProcessingException {
        String users = service.findAll();
        System.out.println(users);
        return users;
    }


    @RequestMapping("/saveUser")
    public String saveUser(@RequestBody User user){
        return "login";
    }
}
