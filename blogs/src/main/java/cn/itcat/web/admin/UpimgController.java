package cn.itcat.web.admin;

import cn.itcat.entity.Image;
import cn.itcat.service.ImgService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UpimgController {
    @Autowired
    private ImgService imgService;
    @Value("${file.upload.path}")
    private String filePath;
    /*跳转图片管理*/
    @RequestMapping("/imgs")
    public String img(Model model) {
        List<Image> images=imgService.images(1,8);
        PageInfo pageInfo=new PageInfo(images);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/upimg";
    }
    /*跳转上传图片*/
    @RequestMapping("/upimg")
    public String upimg() {
        return "admin/shangchuan";
    }
    /*实现上传功能*/
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,RedirectAttributes attributes) throws Exception{
        // 获取上传文件名
        String filename = file.getOriginalFilename();
        // 定义上传文件保存路径
        String path = filePath+"rotPhoto/";
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        // 写入文件
        file.transferTo(new File(path + File.separator + filename));

        // 将src路径发送至html页面
        attributes.addFlashAttribute("filename", "/images/rotPhoto/"+filename);
        String p="/images/rotPhoto/"+filename;
        imgService.saveImg(p,filename);
        return "redirect:/admin/imgs";
    }
    /*跳转上传图片*/
    @RequestMapping("/upimg/{id}/del")
    public String del(@PathVariable Integer id,RedirectAttributes attributes) {
        attributes.addFlashAttribute("message",1);
        imgService.delImg(id);
        return "redirect:/admin/imgs";
    }
}
