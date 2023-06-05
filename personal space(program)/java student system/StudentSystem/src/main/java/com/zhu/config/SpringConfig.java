package com.zhu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.zhu.service"})
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class, MybaitsConfig.class})
@EnableTransactionManagement //事务
public class SpringConfig {

}
