package cn.itcat.web.admin;

import cn.itcat.entity.Blogs;
import cn.itcat.entity.QureyCondition.BlogCondition;
import cn.itcat.entity.User;
import cn.itcat.service.BlogService;
import cn.itcat.service.BlogTagService;
import cn.itcat.service.TagsService;
import cn.itcat.service.TypesService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    private final String LIST="admin/admin";
    private final String INPUT="admin/admininput";
    private final String REDIRECT_ADMIN="redirect:/admin/blogs";
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypesService typesService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private BlogTagService blogTagService;
    /*多条件分页查询*/
    @RequestMapping("/blogs")
    public String blogs(@RequestParam(name="page",required = true,defaultValue = "1")int page,
                        @RequestParam(name="size",required = true,defaultValue = "6")int size,
                        BlogCondition blogs, Model model){
        model.addAttribute("types",typesService.getAllTypes());
        List<Blogs> blogList=blogService.listBlog(page,size,blogs);
        /*赋值给blogs*/
        PageInfo pageInfo=new PageInfo(blogList);
        model.addAttribute("pageInfo",pageInfo);
        return LIST;
    }
    /*异步刷新查询*/
    @RequestMapping("/blogs/search")
    public String search(@RequestParam(name="page",required = true,defaultValue = "1")int page,
                         @RequestParam(name="size",required = true,defaultValue = "9")int size,
                         BlogCondition blogs, Model model, @RequestParam Integer typeid){
        List<Blogs> blogList=blogService.listBlog(page,size,blogs);
        PageInfo pageInfo=new PageInfo(blogList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/admin::blogList";
    }
    /*实现保存博客*/
    @RequestMapping("/blogs/saveBlog")
    public String saveBlog(Blogs blog, HttpSession session,RedirectAttributes attributes){
        if(blog.getBid()==null){
            blog.setUser((User) session.getAttribute("user"));
            blog.setType(typesService.getType(blog.getType().getTypeid()));
            blog.setTagList(tagsService.getByIdsTags(blog.getTagids()));
            int b=blogService.saveBlog(blog);
            /*添加成功后返回blogid，通过得到blogid和的到的tagid更新blog_tags表
             * 实现给博客赋值标签
             * */
            blogTagService.saveBlogTag(blog.getTagids(),blog.getBid());
            if(b>0){
                attributes.addFlashAttribute("message",1);
            }else {
                attributes.addFlashAttribute("message",0);
            }
        }else{
            /*执行修改博客*/
            blogService.updateBlog(blog);
            attributes.addFlashAttribute("message",2);
        }
        return REDIRECT_ADMIN;

    }
    private void setTypeAndTag(Model model){
        model.addAttribute("types",typesService.getAllTypes());/*初始化type*/
        model.addAttribute("tags",tagsService.getAllTags());/*初始化tag*/
    }
    /*跳转编辑博客页面*/
    @RequestMapping("/blogs/editBlog")
    public String editBlog(Model model){
        model.addAttribute("blog",new Blogs());/*初始化blog*/
        setTypeAndTag(model);
        return INPUT;
    }
    /*跳转修改博客页面*/
    @RequestMapping("/blogs/{id}/editBlog")
    public String updateBlog(@PathVariable Integer id, Model model){
        model.addAttribute("blog",blogService.getBlog(id));/*初始化blog*/
        setTypeAndTag(model);
        return INPUT;
    }

    /*删除博客*/
    @RequestMapping("/blogs/{id}/delBlog")
    public String delBlog(@PathVariable Integer id,RedirectAttributes attributes){
        blogService.delBlog(id);
        attributes.addFlashAttribute("message",3);
        return REDIRECT_ADMIN;
    }

}
