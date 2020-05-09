package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/*@ComponentScan({"com.itheima","com.config"})*/
@ComponentScan("com.itheima")
@Import(jdbcConfig.class)
@PropertySource("classpath:jdbcConfig.propertise")
public class SpringConfigruration {

}
