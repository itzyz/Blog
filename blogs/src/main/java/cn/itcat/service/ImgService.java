package cn.itcat.service;

import cn.itcat.entity.Image;

import java.util.List;

public interface ImgService {
    //添加图片
    void saveImg(String path,String filename);
    //删除
    void delImg(Integer id);
    //查询
    List<Image> images(Integer page, Integer size);
}
