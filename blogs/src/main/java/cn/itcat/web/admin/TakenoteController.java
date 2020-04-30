package cn.itcat.web.admin;

import cn.itcat.entity.Takenote;

import cn.itcat.service.TakenoteService;
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
public class TakenoteController {
    /*注入service*/
    @Autowired
    private TakenoteService takenotesService;

    /*查询所有，分页显示*/
    @RequestMapping("/takenote")
    public ModelAndView takenotes(@RequestParam(name="page",required = true,defaultValue = "1")int page,
                                @RequestParam(name="size",required = true,defaultValue = "6")int size){
        ModelAndView mv=new ModelAndView();
        List<Takenote> takenoteList=takenotesService.getAllTakenotes(page,size);
        /*PageInfo是一个分页Bean*/
        PageInfo pageInfo=new PageInfo(takenoteList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("admin/takenotes");
        return mv;
    }

    /*跳转到添加类型页*/
    @RequestMapping("/takenote/takenotesinput")
    public String input(Model model){
        model.addAttribute("takenote",new Takenote());
        return "admin/takenotesinput";
    }
    /*修改
     *1.entity中typename属性设置@NotBlank(message = "类型名称不能为空！")
     *2.在Type前添加@Validated注解开启校验
     *3.定义BindingResult result接收返回结果集，为空的话，获取验证信息
     * */
    @RequestMapping("/takenote/{id}/takenotesinput")
    public String takenoteInput(@PathVariable Integer id, Model model){
        model.addAttribute("takenote",takenotesService.getTakenote(id));
        return "admin/takenotesinput";
    }
    /*
    * 执行修改
    * */
    @RequestMapping("/takenote/{id}")
    public String takenoteUpdate(@PathVariable Integer id,@RequestParam String content,RedirectAttributes attributes){
        takenotesService.updateTakenote(id,content);
        attributes.addFlashAttribute("message",3);
        return "redirect:/admin/takenote";
    }
    /*执行保存类型*/
    @RequestMapping("/takenote/takenoteSave")
    public String takenoSave(Takenote takenote, RedirectAttributes attributes){
        int atakenote=takenotesService.saveTakenote(takenote);
        if(atakenote>0){
            attributes.addFlashAttribute("message",0);
        }else{
            attributes.addFlashAttribute("message",1);
        }
        return "redirect:/admin/takenote";
    }
    /*删除*/
    @RequestMapping("/takenote/{id}/del")
    public String takenoDelete(@PathVariable Integer id,RedirectAttributes attributes){
        takenotesService.deleteTakenote(id);
        attributes.addFlashAttribute("message",2);
        return "redirect:/admin/takenote";
    }

}
