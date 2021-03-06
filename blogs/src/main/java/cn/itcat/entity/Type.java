package cn.itcat.entity;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Type implements Serializable {
    private  Integer  typeid;
   /* @NotBlank(message = "类型名称不能为空！")*/
    private String  typename;
    /*一对多*/
    private List<Blogs> blogs=new ArrayList<>();
    public Type() {
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeid=" + typeid +
                ", typename='" + typename + '\'' +
                ", blogs=" + blogs +
                '}';
    }

    public List<Blogs> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blogs> blogs) {
        this.blogs = blogs;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
