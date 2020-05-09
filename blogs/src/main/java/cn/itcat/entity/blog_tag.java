package cn.itcat.entity;

import java.io.Serializable;
import java.util.List;

public class blog_tag implements Serializable {
    private Integer id;
    private Integer bid;
    private Integer tagid;
    /*多对多*/
    private Blogs blogsList;
    public blog_tag() {
    }

    @Override
    public String toString() {
        return "blog_tag{" +
                "id=" + id +
                ", bid=" + bid +
                ", tagid=" + tagid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public Blogs getBlogsList() {
        return blogsList;
    }

    public void setBlogsList(Blogs blogsList) {
        this.blogsList = blogsList;
    }
}
