package cn.itcat.web.admin;

import cn.itcat.entity.Type;
import cn.itcat.service.TypesService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    /*注入service*/
    @Autowired
    private TypesService typesService;

    /*查询所有，分页显示*/
    @RequestMapping("/type")
    public ModelAndView types(@RequestParam(name="page",required = true,defaultValue = "1")int page,
                              @RequestParam(name="size",required = true,defaultValue = "9")int size){
        ModelAndView mv=new ModelAndView();
        List<Type> typeList=typesService.getAllTypes(page,size);
        /*PageInfo是一个分页Bean*/
        PageInfo pageInfo=new PageInfo(typeList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("admin/types");
        return mv;
    }

    /*跳转到添加类型页*/
    @RequestMapping("/type/typesinput")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/typesinput";
    }
    /*修改
     *1.entity中typename属性设置@NotBlank(message = "类型名称不能为空！")
     *2.在Type前添加@Validated注解开启校验
     *3.定义BindingResult result接收返回结果集，为空的话，获取验证信息
     * */
    @RequestMapping("/type/{id}/typesinput")
    public String typeInput(@PathVariable Integer id, Model model){
        model.addAttribute("type",typesService.getType(id));
        return "admin/typesinput";
    }
    /*
    * 执行修改
    * */
    @RequestMapping("/type/{id}")
    public String typeUpdate(@PathVariable Integer id,@RequestParam String typename,RedirectAttributes attributes){
        typesService.updateType(id,typename);
        attributes.addFlashAttribute("message",3);
        return "redirect:/admin/type";
    }
    /*执行保存类型*/
    @RequestMapping("/type/typeSave")
    public String typeSave(Type type, RedirectAttributes attributes){
        int atype=typesService.saveType(type);
        if(atype>0){
            attributes.addFlashAttribute("message",0);
        }else{
            attributes.addFlashAttribute("message",1);
        }
        return "redirect:/admin/type";
    }
    /*删除*/
    @RequestMapping("/type/{id}/del")
    public String typeDelete(@PathVariable Integer id,RedirectAttributes attributes){
        typesService.deleteType(id);
        attributes.addFlashAttribute("message",2);
        return "redirect:/admin/type";
    }

}
