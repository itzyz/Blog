package cn.itcat.service.impl;

import cn.itcat.dao.TagMapper;
import cn.itcat.entity.Tag;
import cn.itcat.service.TagsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional/*开启事务*/
public class TagServiceImpl implements TagsService {

    @Autowired
    private TagMapper tagMapper;
    /*保存*/
    public int saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    /*根据ID获取*/
    public Tag getTag(Integer tagid) {
        return tagMapper.getByIdTag(tagid);
    }

    /*分页获取所有*/
    public List<Tag> getAllTags(Integer page, Integer size) {
        /*PageNum是页码值，pageSize是每页显示多少条,PageHelper.startPage(page,size);必须写在查询前*/
        PageHelper.startPage(page,size);
        return tagMapper.getAllTags();
    }

    /*获取所有*/
    public List<Tag> getAllTags() {
        return tagMapper.getAllTags();
    }

    /**通过集合id查询*/
    public List<Tag> getByIdsTags(String tagids) {
        return tagMapper.getByIdsTags(coverToList(tagids));
    }
     /*把得到的一串id截取存为数组*/
    private List<Integer> coverToList(String tagids) {
        List<Integer> list=new ArrayList<>();
        if(!"".equals(tagids)&&tagids!=null){
            String[] idarray=tagids.split(",");
            for(int i=0;i<idarray.length;i++){
                list.add(new Integer(idarray[i]));
            }
        }
        return list;
    }
    /*修改*/
    public int updateTag(Integer tagid,String tagname) {
        return tagMapper.updateTag(tagid,tagname);
    }

    /*删除*/
    public void deleteTag(Integer tagid) {
        tagMapper.deleteTag(tagid);
    }
}
