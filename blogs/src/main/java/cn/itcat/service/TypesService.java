package cn.itcat.service;

import cn.itcat.entity.Blogs;
import cn.itcat.entity.Type;

import java.util.List;


public interface TypesService {
    /*保存*/
    int saveType(Type type);

    /*id查询*/
    Type getType(Integer typeid);

    /*分页查询*/
    List<Type> getAllTypes(Integer page,Integer size);

    List<Type> getAllTypes();

    List<Type> getTypes(Integer page,Integer size);
    /*修改*/
    int updateType(Integer typeid,String typename);

    /*删除*/
    void deleteType(Integer typeid);

}
