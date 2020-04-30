package cn.itcat.service.impl;

import cn.itcat.dao.TypeMapper;
import cn.itcat.entity.Type;
import cn.itcat.service.TypesService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional/*开启事务*/
public class TypeServiceImpl implements TypesService {
    @Autowired
    private TypeMapper typeMapper;
    /*保存*/
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    /*根据ID获取*/
    public Type getType(Integer typeid) {
        return typeMapper.getByIdType(typeid);
    }

    /*分页获取所有*/
    public List<Type> getAllTypes(Integer page,Integer size) {
        /*PageNum是页码值，pageSize是每页显示多少条,PageHelper.startPage(page,size);必须写在查询前*/
        PageHelper.startPage(page,size);
        return typeMapper.getAllTypes();
    }
    /*获取所有*/
    public List<Type> getAllTypes() {
        return typeMapper.getAllTypes();
    }

    /*修改*/
    public int updateType(Integer typeid,String typename) {
        return typeMapper.updateType(typeid,typename);
    }

    /*删除*/
    public void deleteType(Integer typeid) {
        typeMapper.deleteType(typeid);
    }
}
