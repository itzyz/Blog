package cn.itcat.dao.provider;

import cn.itcat.entity.Blogs;
import cn.itcat.entity.QureyCondition.BlogCondition;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/*创建SelectProvider类，实现多条件查询*/
public class SelectProviders {
    /*多条件查询*/
    public String listBlog(BlogCondition blog){
        try{
            return new SQL(){
                {
                    if(blog!=null){
                        SELECT("*");
                        FROM("blogs");
                        if(blog.getTitle()!=null && !blog.getTitle().equals("")){
                            /*实现模糊查询LIKE CONCAT('%',#{name},'%') title=#{title}*/
                            WHERE("title LIKE CONCAT('%',#{title},'%')");
                        }
                        if(blog.isRecommend()){
                            WHERE("recommend=#{recommend}");
                        }
                        if(blog.getTypeid()!=null&&!blog.getTypeid().equals("")){
                            WHERE("typeid=#{typeid}");
                        }
                    }else {
                        SELECT("*");
                        FROM("blogs");
                    }
                }
            }.toString();
        }catch (Exception e){
            return "";
        }

    }


}
