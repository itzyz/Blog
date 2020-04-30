package cn.itcat.entity.QureyCondition;

public class BlogCondition {
    private String title;
    private boolean recommend;
    private Integer Typeid;

    public BlogCondition() {
    }

    @Override
    public String toString() {
        return "BlogCondition{" +
                "title='" + title + '\'' +
                ", recommend='" + recommend + '\'' +
                ", Typeid=" + Typeid +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Integer getTypeid() {
        return Typeid;
    }

    public void setTypeid(Integer typeid) {
        Typeid = typeid;
    }
}
