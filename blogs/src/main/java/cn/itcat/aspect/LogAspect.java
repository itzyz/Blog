package cn.itcat.aspect;

import cn.itcat.entity.RequestLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* cn.itcat.web.*.*(..))")
    public void log(){}

    @Before("log()")
    public void Befor(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String url=request.getRequestURL().toString();
        String ip=request.getLocalAddr();
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] ages=joinPoint.getArgs();
        RequestLog requestLog =new RequestLog(url,ip,classMethod,ages);
        logger.info("Request : {}",requestLog);
    }

    @After("log()")
    public void After(){
    }

    @AfterReturning(returning ="result",pointcut = "log()")
    public void AfterReturning(Object result){
       logger.info("Result :{}",result);
    }
}
