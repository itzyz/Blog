package cn.itcat.service.impl;

import cn.itcat.dao.BlogMapper;
import cn.itcat.entity.Blogs;
import cn.itcat.entity.QureyCondition.BlogCondition;
import cn.itcat.entity.blog_tag;
import cn.itcat.handler.NotFoundExcption;
import cn.itcat.service.BlogService;
import cn.itcat.utils.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional/*开启事务*/
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /*根据id查询*/
    public Blogs getBlog(Integer bid) {
            Blogs blogs=blogMapper.getBlog(bid);/*从数据库获取一条博客*/
            List<blog_tag> bt= blogMapper.getBlogTags(bid);/*通过当前bid获取博客标签*/
            blogs.setTagids(tagToids(bt));/*将返回的标签数组转为字符串，传入blog中的标签作为前端下拉框中显示的value值*/
            System.out.println(blogs);
            System.out.println("从数据库中获取数据");
            return blogs;
    }
    /*将数组ids变为字符串 1,2,3,*/
    private String tagToids(List<blog_tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids=new StringBuffer();
            boolean flag=false;
            for (blog_tag tag:tags){
                if(flag){
                    ids.append(",");
                }else{
                    flag=true;
                }
                ids.append(tag.getTagid());
            }
            return ids.toString();
        }else{
            return null;
        }
    }
    /*分页查询+条件查询*/
    public List<Blogs> listBlog(Integer page, Integer size, BlogCondition blogs) {
            PageHelper.startPage(page,size);
            return  blogMapper.listBlog(blogs);
    }
    /*根据统一typeid查询blog*/
    public List<Blogs> getByTypeidBlog(Integer typeid) {
        return blogMapper.getByTypeidBlog(typeid);
    }

    /*根据年份显示*/
    public Map<String, List<Blogs>> archiveBlog() {
        List<String> years=blogMapper.findGroupYear();
        Map<String,List<Blogs>> map=new HashMap<>();
        for(String year:years){
            map.put(year,blogMapper.findByYear(year));
        }
        return map;
    }

    /*分页查询按推荐查询*/
    public List<Blogs> recommendlistBlog(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return blogMapper.getRecommendBlog();
    }

    /*保存*/
    public int saveBlog(Blogs blog) {
            blog.setViews(0);
            return blogMapper.saveBlog(blog);
    }

    /*获取并转换markdowncontent*/
    public Blogs getAndConvert(Integer bid) {
        Blogs blog=blogMapper.getBlog(bid);
        if(blog==null){
            throw new NotFoundExcption("该博客不存在！");
        }
        Blogs b = new Blogs();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogMapper.updateViews(bid);
        return b;
    }
    /*查询博客个数*/
    public Integer countBlog() {
        return blogMapper.getBlogCount();
    }
    /*修改*/
    public int updateBlog(Blogs blog) {
        return blogMapper.updateBlog(blog);
    }

    /*删除*/
    public void delBlog(Integer bid) {
        blogMapper.delBlog(bid);
    }
}
