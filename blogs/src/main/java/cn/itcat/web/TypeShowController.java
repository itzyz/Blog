
package cn.itcat.web;


import cn.itcat.entity.Blogs;
import cn.itcat.entity.QureyCondition.BlogCondition;
import cn.itcat.entity.Type;
import cn.itcat.service.BlogService;
import cn.itcat.service.TypesService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypesService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(name="page",required = true,defaultValue = "1")int page,
                        @RequestParam(name="size",required = true,defaultValue = "6")int size,
                        @PathVariable Integer id, Model model) {
      List<Type> types = typeService.getAllTypes();
            if (id == -1) {
                id = types.get(0).getTypeid();
            }
            BlogCondition blogQuery = new BlogCondition();
            blogQuery.setTypeid(id);
            model.addAttribute("types", types);
            /*blog存入pageInfo*/
            List<Blogs> blogs= blogService.listBlog(page,size,blogQuery);
            PageInfo pageBInfo=new PageInfo(blogs);
            model.addAttribute("pageInfo",pageBInfo);
            model.addAttribute("activeTypeId", id);
            return "types";
        }
}

