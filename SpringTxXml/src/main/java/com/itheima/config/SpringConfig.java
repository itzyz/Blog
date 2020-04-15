package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.itheima")
@Import({jdbcConfig.class,TracnsactionConfig.class})
@PropertySource("jdbcConfig.propertise")
@EnableTransactionManagement
public class SpringConfig {
}
