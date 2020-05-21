package cn.itcat.service.impl;

import cn.itcat.dao.ImgMapper;
import cn.itcat.entity.Image;
import cn.itcat.service.ImgService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ImgServiceImpl implements ImgService {
    @Autowired
    private ImgMapper imgMapper;

    public void saveImg(String path,String filename) {
        imgMapper.saveImg(path,filename);
    }

    public void delImg(Integer id) {
        imgMapper.delImg(id);
    }

    public List<Image> images(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return imgMapper.images();
    }
}
