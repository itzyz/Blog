package com.itcast.controller;

import com.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class ControllerAjax {

    @RequestMapping("/TestAjax")
    public @ResponseBody User TestAjax(@RequestBody User user){
        user.setUsername("josn");
        user.setPassword("3456");
        user.setAge(20);
        System.out.println(user);
        return user;
    }
}
