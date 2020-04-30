package cn.itcat.dao;

import cn.itcat.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {

    /*保存*/
    @Insert("insert into tag(tagid,tagname) values(#{tagid},#{tagname})")
    int saveTag(Tag tag);

    /*id查询*/
    @Select("select * from Tag where Tagid=#{Tagid}")
    Tag getByIdTag(Integer tagid);

    /**通过集合id查询*/
    @Select("<script>"
               + "SELECT * FROM tag WHERE tagid IN "
               + "<foreach collection='tagids' item='item' index='index' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>"+
            "</script>")
    List<Tag> getByIdsTags(@Param("tagids") List<Integer> tagids);



    /*分页查询*/
    @Select("select * from Tag")
    List<Tag> getAllTags();

    /*修改*/
    @Update("update tag set tagname=#{tagname} where tagid=#{tagid}")
    int updateTag(Integer tagid, String tagname);

    /*删除*/
    @Delete("delete from tag where tagid=#{tagid} ")
    void deleteTag(Integer tagid);
}
