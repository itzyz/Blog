package com.itcast.controller;

import com.itcast.doman.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;


@Controller
/*把数据存入session*/
@SessionAttributes(value = {"username","password","age"},types= {String.class,Integer.class})
public class AnnoController {
    @RequestMapping("/RequestBody")
    public String testRequestBody(@RequestBody String user){
        System.out.println("testRequestBody...");
        System.out.println(user);
        return "success";
    }

    @RequestMapping("/RequestParam")
    public String testRequestParam(@RequestParam("username") String name,@RequestParam("password") String pwb){
        System.out.println("testRequestParam...");
        System.out.println(name);
        System.out.println(pwb);
        return "success";
    }

    @RequestMapping("/CookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String cookiValue){
        System.out.println("testCookieValue...");
        System.out.println(cookiValue);
        return "success";
    }

    @RequestMapping("/ModelAttribute")
    public String testModelAttribute(User user){
        System.out.println("ModelAttribute...");
        System.out.println(user);
        return "success";
    }

    @ModelAttribute
    public User showUser(String name){
        User user =new User();
        user.setAge(18);
        user.setUsername("张三");
        return user;
    }


    @RequestMapping("/saveSessionAttributes")
    public String testSaveSessionAttributes(Model model){
        System.out.println("testCookieValue...");
        model.addAttribute("username","root");
        model.addAttribute("password","root");
        model.addAttribute("age",20);
        return "success";
    }

    @RequestMapping("/findSessionAttributes")
    public String testfindSessionAttributes(ModelMap modelMap){
        System.out.println("testCookieValue...");
        String username = (String)modelMap.get("username");
        String password = (String)modelMap.get("password");
        String age = (String)modelMap.get("age");
        System.out.println(username);
        System.out.println(password);
        System.out.println(age);
        return "success";
    }

    @RequestMapping(path="/delete")
    public String delete(SessionStatus status) {
        status.setComplete();
        return "success";
    }
}




