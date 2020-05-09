package cn.itcat.entity;

import java.io.Serializable;
import java.util.Date;

public class Takenote implements Serializable {
    private Integer tid;
    private String content;
    private Date cratetime;
    /*多对一*/
    private User user;
    public Takenote() {
    }

    @Override
    public String toString() {
        return "Takenote{" +
                "tid=" + tid +
                ", content='" + content + '\'' +
                ", cratetime=" + cratetime +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCratetime() {
        return cratetime;
    }

    public void setCratetime(Date cratetime) {
        this.cratetime = cratetime;
    }
}
