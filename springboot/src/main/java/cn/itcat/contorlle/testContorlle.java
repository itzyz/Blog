package cn.itcat.contorlle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testContorlle {
    @RequestMapping("/user")
    @ResponseBody
    public String test(){
        return "hello springboot2";
    }
}
