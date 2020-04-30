package cn.itcat.dao;

import cn.itcat.dao.provider.SelectProviders;
import cn.itcat.entity.Blogs;
import cn.itcat.entity.blog_tag;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface BlogTagMapper {
    /*一个博客所对应多个标签插入*/
    @Insert("<script>"
            + "<foreach collection='ids' item='item' index='index' open=' ' separator=';' close=' '>" +
                    "INSERT INTO blog_tags(bid,tagid) VALUES (#{bid},#{item})" +
               "</foreach> "+
            "</script>")
    int insertBlogTag(@Param("ids") List<Integer> ids,Integer bid);

    /*通过博客id查询标签*/
    @Select("select * from blog_tag where bid=#{bid}")
    List<blog_tag> getByBidTag(Integer bid);
}
