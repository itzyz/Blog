package cn.itcat.dao;

import cn.itcat.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@CacheNamespace
public interface CommentMapper {

    /*获取blog下的评论ORDER BY cid DES C   ORDER BY cid ASC*/
    @Select("select * from comment where bid=#{bid}  and parentcommentid IS NULL ORDER BY cid DESC")
    @Results({
            @Result(property = "blogs", column = "bid",
                    one = @One(fetchType = FetchType.LAZY ,select = "cn.itcat.dao.BlogMapper.getABlog"))
    })
    List<Comment> listCommentByBlogid(Integer bid);

    /*获取二级回复*/
    @Select("SELECT * FROM comment WHERE parentcommentid=#{cid} AND parentcommentid IS NOT NULL")
    List<Comment> listCommentReplyByCid(Integer cid);

    /*获取一条评论对象*/
    @Select("select * from comment where cid=#{cid}")
    Comment getAcomment(Integer cid);

    /*保存评论*/
    @Insert("INSERT INTO comment(nickname,email,content,avatar,bid,parentcommentid,admincomment) " +
            " VALUES(#{nickname},#{email},#{content},#{avatar},#{blogs.bid},#{parentComment.cid},#{adminComment}) ")
    int saveComment(Comment comment);
}
