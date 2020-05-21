package cn.itcat.service.impl;

import cn.itcat.dao.CommentMapper;
import cn.itcat.entity.Comment;
import cn.itcat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * @Description: 查询评论
     * @Auther: ONESTAR
     * @Date: 17:26 2020/4/14
     * @Param:
     * @Return: 评论消息
     */
     public List<Comment> listCommentByBlogid(Integer bid) {
        //查询出父节点
        List<Comment> comments =commentMapper.listCommentByBlogid(bid);
        for(Comment comment: comments){
            Integer id = comment.getCid();
            String parentNickname1 = comment.getNickname();
            List<Comment> childComments = commentMapper.listCommentReplyByCid(id);
            //查询出子评论
            combineChildren(childComments,parentNickname1);
            comment.setReplyComment(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    /**
     * @Description: 查询出子评论
     * @Auther: ONESTAR
     * @Date: 17:31 2020/4/14
     * @Param: childComments：所有子评论
     * @Param: parentNickname1：父评论的姓名
     * @Return:
     */
    private void combineChildren(List<Comment> childComments, String parentNickname1) {
        //判断是否有一级子回复
        if(childComments.size() > 0){
            //循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentnickname(parentNickname1);
                tempReplys.add(childComment);
                Integer childId = childComment.getCid();
                //查询二级以及所有子集回复
                recursively(childId, parentNickname);
            }
        }
    }
    /**
     * @Description: 循环迭代找出子集回复
     * @Auther: ONESTAR
     * @Date: 17:33 2020/4/14
     * @Param: childId：子评论的id
     * @Param: parentNickname1：子评论的姓名
     * @Return:
     */
     private void recursively(Integer childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.listCommentReplyByCid(childId);
        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentnickname(parentNickname1);
                Integer replayId = replayComment.getCid();
                tempReplys.add(replayComment);
                //循环迭代找出子集回复
                recursively(replayId,parentNickname);
            }
        }
    }

    /*保存评论*/
    @Transactional
    public int saveComment(Comment comment) {
        Integer parentCommentId=comment.getParentComment().getCid();
        if(parentCommentId!=-1){
            comment.setParentComment(commentMapper.getAcomment(parentCommentId));
        }else{
            comment.setParentComment(null);
        }
        return commentMapper.saveComment(comment);
    }
}
