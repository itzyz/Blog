package cn.itcat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/*自定义异常类监听每一个Controller*/
@ControllerAdvice
public class ExceptionContorller  {
    //定义后台异常日志
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView Exception(HttpServletRequest request, Exception e) throws Exception {
        /*记录后台日志*/
        logger.error("Requset URL : {},Exception : {}",request.getRequestURL(),e);
        /*判断是否有注解添加状态码*/
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        /*返回到error*/
        ModelAndView mv=new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("excption",e);
        mv.setViewName("error/error");
        return mv;
    }
}
