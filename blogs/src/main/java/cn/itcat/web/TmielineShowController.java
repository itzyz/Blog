package cn.itcat.web;

import cn.itcat.entity.Takenote;
import cn.itcat.service.TakenoteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TmielineShowController {
    @Autowired
    private TakenoteService takenoteService;

    @RequestMapping("/timeline")
    public String timeline(Model model){
        List<Takenote> takenote=takenoteService.getAllTakenotes(1,50);
        PageInfo pageInfo=new PageInfo(takenote);
        model.addAttribute("takenoteInfo",pageInfo);
        return "timeline";
    }
}
