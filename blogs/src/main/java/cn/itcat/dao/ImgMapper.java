package cn.itcat.dao;

import cn.itcat.entity.Image;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ImgMapper {
    //添加图片
    @Insert("insert into img(path,filename) values(#{path},#{filename})")
    void saveImg(String path,String filename);
    //删除
    @Delete("delete from img where id=#{id} ")
    void delImg(Integer id);
    //查询
    @Select("select * from img")
    List<Image> images();
}
