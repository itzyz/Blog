package com.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class Upload {
    /*
    * SpringMVC上传文件
    * */
    @RequestMapping("/TestUpload")
    public String TestUpload(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("上传文件执行..");
        /*上传文件夹de路径*/
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(path);
        if(!file.exists()){
            // 创建该文件夹
            file.mkdirs();
        }
        /*说明上传项，获取文件夹名称*/
        String filename = upload.getOriginalFilename();
        /*设置文件名唯一*/
        String uuid = UUID.randomUUID().toString();
        filename=uuid+"_"+filename;
        /*完成文件上传*/
        upload.transferTo(new File(path,filename));
        System.out.println(filename);
        System.out.println(path);
        return "success";
    }
}
