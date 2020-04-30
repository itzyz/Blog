package cn.itcat.service.impl;

import cn.itcat.dao.BlogMapper;
import cn.itcat.entity.Blogs;
import cn.itcat.entity.QureyCondition.BlogCondition;
import cn.itcat.entity.blog_tag;
import cn.itcat.service.BlogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional/*开启事务*/
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    /*根据id查询*/
    public Blogs getBlog(Integer bid) {
        Blogs blogs=blogMapper.getBlog(bid);/*获取一条博客*/
        List<blog_tag> bt= blogMapper.getBlogTags(bid);/*通过当前bid获取博客标签*/
        blogs.setTagids(tagToids(bt));/*将返回的标签数组转为字符串，传入blog中的标签作为前端下拉框中显示的value值*/
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
        return blogMapper.listBlog(blogs);
    }

    /*保存*/
    public int saveBlog(Blogs blog) {
            blog.setViews(0);
            return blogMapper.saveBlog(blog);
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
