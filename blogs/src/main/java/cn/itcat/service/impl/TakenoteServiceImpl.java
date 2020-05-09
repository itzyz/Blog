package cn.itcat.service.impl;


import cn.itcat.dao.TakenoteMapper;
import cn.itcat.entity.Takenote;
import cn.itcat.service.TakenoteService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional/*开启事务*/
public class TakenoteServiceImpl implements TakenoteService {
    @Autowired
    private TakenoteMapper takenoteMapper;
    /*保存*/
    public int saveTakenote(Takenote takenote) {
        return takenoteMapper.saveTakenote(takenote);
    }

    /*根据ID获取*/
    public Takenote getTakenote(Integer takenoteid) {
        return takenoteMapper.getByIdTakenote(takenoteid);
    }

    /*获取所有*/

    public List<Takenote> getAllTakenotes(Integer page,Integer size) {
        /*PageNum是页码值，pageSize是每页显示多少条,PageHelper.startPage(page,size);必须写在查询前*/
        PageHelper.startPage(page,size);
        return takenoteMapper.getAllTakenotes();
    }

    /*修改*/
    public int updateTakenote(Integer takenoteid,String content) {
        return takenoteMapper.updateTakenote(takenoteid,content);
    }

    /*删除*/
    public void deleteTakenote(Integer takenoteid) {
        takenoteMapper.deleteTakenote(takenoteid);
    }
}
