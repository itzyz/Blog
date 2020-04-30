package cn.itcat.service;

import cn.itcat.entity.Blogs;
import cn.itcat.entity.QureyCondition.BlogCondition;
import cn.itcat.entity.blog_tag;

import java.util.List;

public interface BlogService {
    /*根据id查询博客*/
    Blogs getBlog(Integer bid);

    /*分页查询Blog*/
    List<Blogs> listBlog(Integer page, Integer size, BlogCondition blogs);

    /*保存*/
    int saveBlog(Blogs blog);

    /*修改*/
    int updateBlog(Blogs blog);

    /*删除*/
    void delBlog(Integer bid);

}
