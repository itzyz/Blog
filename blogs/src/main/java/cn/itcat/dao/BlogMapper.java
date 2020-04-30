package cn.itcat.dao;

import cn.itcat.dao.provider.SelectProviders;
import cn.itcat.entity.Blogs;
import cn.itcat.entity.blog_tag;
import cn.itcat.entity.QureyCondition.BlogCondition;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    /*根据id查询博客*/
    @Select("SELECT * FROM blogs WHERE bid=#{bid}")
    @Results({
            @Result(property = "type", column = "typeid",
                    one = @One(fetchType = FetchType.LAZY ,select = "cn.itcat.dao.TypeMapper.getByIdType")),
    })
    Blogs getBlog(Integer bid);
    /*根据当前博客id查询博客标签*/
    @Select("SELECT * FROM blog_tags WHERE bid=#{bid}")
    List<blog_tag> getBlogTags(Integer bid);

    /*分页查询Blog*/
    @SelectProvider(type = SelectProviders.class,method = "listBlog")
    @Results({
            @Result(property = "type", column = "typeid",
                    one = @One(fetchType = FetchType.LAZY ,select = "cn.itcat.dao.TypeMapper.getByIdType"))
    })
    List<Blogs> listBlog(BlogCondition blogs);

    /*保存*/
    @Insert("INSERT INTO blogs(title,content,firstpicture,flag,views,appreciation,sharestatement,commentabled,published,recommend,typeid,uid)" +
            " VALUES(#{title},#{content},#{firstpicture},#{flag},#{views},#{appreciation},#{sharestatement},#{commentabled},#{published},#{recommend},#{type.typeid},#{user.uid})")
    @Options(useGeneratedKeys=true,keyProperty="bid",keyColumn="bid")
    int saveBlog(Blogs blog);

    /*修改*/
    @Update("UPDATE blogs SET title=#{title},content=#{content},firstpicture=#{firstpicture},flag=#{flag},appreciation=#{appreciation},sharestatement=#{sharestatement}, commentabled=#{commentabled},published=#{published},recommend=#{recommend},typeid=#{type.typeid} WHERE bid=#{bid}")
    int updateBlog(Blogs blog);

    /*删除*/
    @Delete("delete from blogs where bid=#{bid}")
    void delBlog(Integer bid);

}
