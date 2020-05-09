package cn.itcat.web;

import cn.itcat.entity.Comment;
import cn.itcat.entity.User;
import cn.itcat.service.BlogService;
import cn.itcat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class commentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;
    @Value("${comment.meavatar}")
    private String meavatar;

    @RequestMapping("/comments/{blogid}")
    public String comments(@PathVariable Integer blogid,Model model){
        model.addAttribute("comments",commentService.listCommentByBlogid(blogid));
        return "blogdetail::commentList";
    }

    @RequestMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Integer bid=comment.getBlogs().getBid();
        comment.setBlogs(blogService.getBlog(bid));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(meavatar);
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+bid;
    }
}
