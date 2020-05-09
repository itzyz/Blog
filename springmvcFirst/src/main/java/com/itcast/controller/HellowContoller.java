package com.itcast.controller;

import com.itcast.doman.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HellowContoller {
   @RequestMapping(path = "/hellow")
    public String HellowSpring(){
        System.out.println("成功！");
        return "success";
    }
    @RequestMapping(path = "/userLogin")
    public String userLogin(String username,String password){
        System.out.println("成功！");
        System.out.println(username);
        System.out.println(password);
        return "success";
    }
    @RequestMapping(path = "/saveUser")
    public String saveUser(User user){
            System.out.println("成功！");
            System.out.println(user);
        return "success";
    }
}
