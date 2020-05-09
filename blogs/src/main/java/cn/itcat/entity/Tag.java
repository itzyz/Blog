package cn.itcat.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tag implements Serializable {
    private Integer tagid;
    private String tagname;
    /*多对多*/
    private List<Blogs> blogs=new ArrayList<>();
    public Tag() {
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagid=" + tagid +
                ", tagname='" + tagname + '\'' +
                ", blogs=" + blogs +
                '}';
    }

    public List<Blogs> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blogs> blogs) {
        this.blogs = blogs;
    }

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
}
