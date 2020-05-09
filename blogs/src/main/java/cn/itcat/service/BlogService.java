package cn.itcat.service;

import cn.itcat.entity.Blogs;
import cn.itcat.entity.QureyCondition.BlogCondition;

import java.util.List;
import java.util.Map;

public interface BlogService {
    /*根据id查询博客*/
    Blogs getBlog(Integer bid);
    /*分页查询Blog*/
    List<Blogs> listBlog(Integer page, Integer size, BlogCondition blogs);
    /*分页查询Blog*/
    List<Blogs> recommendlistBlog(Integer page, Integer size);
    /*根据typeid查询*/
    List<Blogs> getByTypeidBlog(Integer typeid);
    /*根据年份显示*/
    Map<String,List<Blogs>> archiveBlog();
    /*保存*/
    int saveBlog(Blogs blog);
    /*获取并转换markdowncontent*/
    Blogs getAndConvert(Integer bid);
    /*查询博客个数*/
    Integer countBlog();
    /*修改*/
    int updateBlog(Blogs blog);
    /*删除*/
    void delBlog(Integer bid);

}
