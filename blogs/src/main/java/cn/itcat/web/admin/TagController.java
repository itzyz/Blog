package cn.itcat.web.admin;

import cn.itcat.entity.Tag;
import cn.itcat.service.TagsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    /*注入service*/
    @Autowired
    private TagsService tagsService;

    /*查询所有，分页显示*/
    @RequestMapping("/tag")
    public ModelAndView tags(@RequestParam(name="page",required = true,defaultValue = "1")int page,
                              @RequestParam(name="size",required = true,defaultValue = "9")int size){
        ModelAndView mv=new ModelAndView();
        List<Tag> tagList=tagsService.getAllTags(page,size);
        /*PageInfo是一个分页Bean*/
        PageInfo pageInfo=new PageInfo(tagList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("admin/tags");
        return mv;
    }

    /*跳转到添加类型页*/
    @RequestMapping("/tag/tagsinput")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tagsinput";
    }
    /*修改
     *1.entity中tagname属性设置@NotBlank(message = "类型名称不能为空！")
     *2.在tag前添加@Validated注解开启校验
     *3.定义BindingResult result接收返回结果集，为空的话，获取验证信息
     * */
    @RequestMapping("/tag/{id}/tagsinput")
    public String tagInput(@PathVariable Integer id, Model model){
        model.addAttribute("tag",tagsService.getTag(id));
        return "admin/tagsinput";
    }
    /*
    * 执行修改
    * */
    @RequestMapping("/tag/{id}")
    public String tagUpdate(@PathVariable Integer id,@RequestParam String tagname,RedirectAttributes attributes){
        tagsService.updateTag(id,tagname);
        attributes.addFlashAttribute("message",3);
        return "redirect:/admin/tag";
    }
    /*执行保存类型*/
    @RequestMapping("/tag/tagSave")
    public String tagSave(Tag tag, RedirectAttributes attributes){
        int atag=tagsService.saveTag(tag);
        if(atag>0){
            attributes.addFlashAttribute("message",0);
        }else{
            attributes.addFlashAttribute("message",1);
        }
        return "redirect:/admin/tag";
    }
    /*删除*/
    @RequestMapping("/tag/{id}/del")
    public String tagDelete(@PathVariable Integer id,RedirectAttributes attributes){
        tagsService.deleteTag(id);
        attributes.addFlashAttribute("message",2);
        return "redirect:/admin/tag";
    }

}
