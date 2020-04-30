package cn.itcat.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/*定义配置类*/
/*WebMvcConfigurerAdapter sprngboot2.0x这个方法过时t*/
/*继承WebMvcConfigurationSuppor类是需要使用@EnableMvc注解会使所有springboot配置失效*/
/*WebMvcConfigurer是WebMvcConfigurationSuppor的扩展类*/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /*配置拦截器生效*/
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")/*拦截所有admin下的资源*/
                .excludePathPatterns("/admin")/*排除admin*/
                .excludePathPatterns("/admin/login");/*排除提交登录表单地址*/
    }
}
