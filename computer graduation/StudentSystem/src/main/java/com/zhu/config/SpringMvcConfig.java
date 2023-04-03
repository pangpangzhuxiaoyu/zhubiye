package com.zhu.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.SimpleDateFormat;

@Configuration
@ComponentScan({"com.zhu.controller","com.zhu.config"})
@EnableWebMvc
public class SpringMvcConfig {

    //当Spring MVC处理返回JSON数据的请求时，Jackson使用自定义的日期格式来格式化日期。
    @Bean
    public MappingJackson2HttpMessageConverter jsonConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        return converter;
    }

}
