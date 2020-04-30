package cn.itcat.service.impl;

import cn.itcat.dao.BlogTagMapper;
import cn.itcat.entity.Blogs;
import cn.itcat.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BlogTagServiceImpl implements BlogTagService {
    @Autowired
    private BlogTagMapper blogTagMapper;
    /*往博客中插入标签*/
    public int saveBlogTag(String ids, Integer bid) {
        return blogTagMapper.insertBlogTag(coverToList(ids),bid);
    }
    /*把得到的一串id截取存为数组*/
    private List<Integer> coverToList(String ids) {
        List<Integer> list=new ArrayList<>();
        if(!"".equals(ids)&&ids!=null){
            String[] idarray=ids.split(",");
            for(int i=0;i<idarray.length;i++){
                list.add(new Integer(idarray[i]));
            }
        }
        return list;
    }
}
