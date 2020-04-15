package com.itheima.utils;

public class logger {
/*配置前置通知*/
    public void beforLogger(){
        System.out.println("日志记录启动成功....");
    }
/*后置通知*/
    public void afterRunningLogger(){
        System.out.println("日志记录开始");
    }
/*异常通知*/
    public void thorwingLogger(){
        System.out.println("日志记录异常");
    }
/*最终通知*/
    public void afterLogger(){
        System.out.println("日志记录结束..");
    }
}
