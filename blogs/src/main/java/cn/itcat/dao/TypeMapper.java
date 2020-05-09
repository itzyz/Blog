package cn.itcat.dao;

import cn.itcat.entity.Type;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
@CacheNamespace
public interface TypeMapper {

    /*保存*/
    @Insert("insert into type(typeid,typename) values(#{typeid},#{typename})")
    int saveType(Type type);

    /*id查询*/
    @Select("select * from type where typeid=#{typeid}")
    Type getByIdType(Integer typeid);

    /*分页查询*/
    @Select("select * from type")
    List<Type> getAllTypes();

    /*
    * 查询绑定
    * */
    /*分页查询*/
    @Select("select * from type")
    @Results({
            @Result(property = "blogs", column = "typeid",
                    many = @Many(select = "cn.itcat.dao.BlogMapper.getByTypeidBlog"))
    })
    List<Type> getTypes();
    /*修改*/
    @Update("update type set typename=#{typename} where typeid=#{typeid}")
    int updateType(Integer typeid,String typename);

    /*删除*/
    @Delete("delete from type where typeid=#{typeid} ")
    void deleteType(Integer typeid);
}
