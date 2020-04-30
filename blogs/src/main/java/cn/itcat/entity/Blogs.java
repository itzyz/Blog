package cn.itcat.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Blogs implements Serializable {

    private Integer bid;
    private String title;
    private String content;
    private String firstpicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean sharestatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    private String cratetime;
    private String tagids;
    /*博客类型多对一*/
    private Type type=new Type();
    /*博客标签多对多*/
    private List<Tag> tagList=new ArrayList<>();
    /*博客标签表对应关系*/
    private List<blog_tag> blog_tag=new ArrayList<>();
    /*多对一*/
    private User user;
    /*一对多*/
    private List<Comment> comments=new ArrayList<>();

    public Blogs() {
    }
    /*初始化tagids
    public void init(){
        this.tagids=this.tagToids(this.getBlog_tag());
        System.out.println("====="+this.tagids);
    }*/
    /*将ids变为 1,2,3,*/
    /*private String tagToids(List<blog_tag> tags){
        System.out.println("tagToids:"+tags);
        if(!tags.isEmpty()){
            StringBuffer ids=new StringBuffer();
            boolean flag=false;
            for (blog_tag tag:tags){
                if(flag){
                    ids.append(",");
                }else{
                    flag=true;
                }
               ids.append(tag.getTagid());
            }
            return ids.toString();
        }else{
            return tagids;
        }
    }*/
    public String getTagids() {
        return tagids;
    }

    public void setTagids(String tagids) {
        this.tagids =tagids;
    }
    @Override
    public String toString() {
        return "Blogs{" +
                "bid=" + bid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstpicture='" + firstpicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views='" + views + '\'' +
                ", appreciation=" + appreciation +
                ", sharestatement=" + sharestatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", cratetime=" + cratetime +
                ", type=" + type +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }

    public List<cn.itcat.entity.blog_tag> getBlog_tag() {
        return blog_tag;
    }

    public void setBlog_tag(List<cn.itcat.entity.blog_tag> blog_tag) {
        this.blog_tag = blog_tag;
    }


    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstpicture() {
        return firstpicture;
    }

    public void setFirstpicture(String firstpicture) {
        this.firstpicture = firstpicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isSharestatement() {
        return sharestatement;
    }

    public void setSharestatement(boolean sharestatement) {
        this.sharestatement = sharestatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public String getCratetime() {
        return cratetime;
    }

    public void setCratetime(String cratetime) {
        this.cratetime = cratetime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
