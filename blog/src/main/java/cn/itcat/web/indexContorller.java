package cn.itcat.web;

import cn.itcat.handler.NotFoundExcption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexContorller {

    @RequestMapping({"/index","/"})
    public String index(){
        /*int i=9/0;*/
        String blog="1";
        if(blog==null){
            throw new NotFoundExcption("博客不存在");
        }
        return "idnex";
    }
}
