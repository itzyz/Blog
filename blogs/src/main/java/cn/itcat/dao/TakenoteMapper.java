package cn.itcat.dao;

import cn.itcat.entity.Takenote;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TakenoteMapper {

    /*保存*/
    @Insert("insert into takenote(tid,content) values(#{tid},#{content})")
    int saveTakenote(Takenote takenote);

    /*id查询*/
    @Select("select * from takenote where tid=#{takenoteid}")
    Takenote getByIdTakenote(Integer takenoteid);

    /*分页查询*/
    @Select("SELECT * FROM takenote GROUP BY tid DESC")
    List<Takenote> getAllTakenotes();

    /*修改*/
    @Update("update takenote set content=#{content} where tid=#{takenoteid}")
    int updateTakenote(Integer takenoteid, String content);

    /*删除*/
    @Delete("delete from takenote where tid=#{takenoteid} ")
    void deleteTakenote(Integer takenoteid);
}
