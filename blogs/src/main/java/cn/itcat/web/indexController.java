package cn.itcat.web;

import cn.itcat.entity.Blogs;
import cn.itcat.entity.QureyCondition.BlogCondition;
import cn.itcat.entity.Tag;
import cn.itcat.entity.Type;
import cn.itcat.service.BlogService;
import cn.itcat.service.TagsService;
import cn.itcat.service.TypesService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class indexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypesService typesService;
    @Autowired
    private TagsService tagsService;
    @GetMapping("/")
    public String index(@RequestParam(name="page",required = true,defaultValue = "1")int page,
                        @RequestParam(name="size",required = true,defaultValue = "5")int size,
                        BlogCondition blogs, Model model){
        /*分页获取类型*/
        List<Type> typeList=typesService.getTypes(page,6);
        PageInfo pageTypeInfo=new PageInfo(typeList);
        model.addAttribute("pageTypeInfo",pageTypeInfo);
        /*分页获取标签*/
        List<Tag> tagList=tagsService.getAllTags(page,11);
        PageInfo pageTagInfo=new PageInfo(tagList);
        model.addAttribute("pageTagInfo",pageTagInfo);
        /*赋值给blogs
         *按推荐分页查询博客
         */
        List<Blogs> recommendList=blogService.recommendlistBlog(page,8);
        PageInfo pageRecommendInfo=new PageInfo(recommendList);
        model.addAttribute("pageRecommendInfo",pageRecommendInfo);
        /*赋值给blogs
        *分页查询博客
         */
        List<Blogs> blogList=blogService.listBlog(page,size,blogs);
        PageInfo pageInfo=new PageInfo(blogList);
        model.addAttribute("pageInfo",pageInfo);
        /*计数*/
        List<Blogs> b=blogService.listBlog(1,100,blogs);
        PageInfo pageBInfo=new PageInfo(b);
        model.addAttribute("pageBInfo",pageBInfo);
        return "index";
    }
    /*详情页*/
    @RequestMapping("/blog/{id}")
    public String blog(@PathVariable Integer id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blogdetail";
    }

 /*   @RequestMapping("/footer")
    public String newblog(Model model){
        List<Blogs> blogs=blogService.recommendlistBlog(1,3);
        PageInfo page=new PageInfo(blogs);
        model.addAttribute("footerpageInfo",page);
        System.out.println(page);
        return "_fragment::newblogList";
    }*/

}
