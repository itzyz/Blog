package cn.itcat.service;

import cn.itcat.entity.Takenote;

import java.util.List;


public interface TakenoteService {
    /*保存*/
    int saveTakenote(Takenote takeno);

    /*id查询*/
    Takenote getTakenote(Integer takenoteid);

    /*分页查询*/
    List<Takenote> getAllTakenotes(Integer page, Integer size);

    /*修改*/
    int updateTakenote(Integer takenoteid, String content);

    /*删除*/
    void deleteTakenote(Integer takenoteid);

}
