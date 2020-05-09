package cn.itcat.service;

import cn.itcat.entity.Comment;

import java.util.List;

public interface CommentService {
    /*获取blog下的评论*/
    List<Comment> listCommentByBlogid(Integer bid);

    /*保存评论*/
    int saveComment(Comment comment);



}
