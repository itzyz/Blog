package cn.itcat.web.admin;

import cn.itcat.entity.User;
import cn.itcat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;
    @GetMapping
    public String LoginPage(){
        return "login_regist";
    }

    /*登录*/
    @RequestMapping("/login")
    public String Login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        User user=userService.checkUser(username,password);
        if(user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/admin";
        }else{
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    /*注销*/
    @RequestMapping("/loginOut")
    public String LoginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
