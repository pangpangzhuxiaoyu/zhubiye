package com.zhu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.zhu.service"})
@PropertySource("jdbc.properties")
@Import({JdbcConfig.class, MybaitsConfig.class})
public class SpringConfig {

}
