package cn.itcat.service;

import cn.itcat.entity.Tag;
import java.util.List;


public interface TagsService {
    /*保存*/
    int saveTag(Tag tag);

    /*id查询*/
    Tag getTag(Integer Tagid);

    /*分页查询*/
    List<Tag> getAllTags(Integer page, Integer size);

    /*查询所有*/
    List<Tag> getAllTags();

    /**通过集合id查询*/
    List<Tag> getByIdsTags(String tagids);

    /*修改*/
    int updateTag(Integer tagid, String tagname);

    /*删除*/
    void deleteTag(Integer tagid);

}
