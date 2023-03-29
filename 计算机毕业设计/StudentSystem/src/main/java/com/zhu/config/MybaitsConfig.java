package com.zhu.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MybaitsConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean factory =new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        //类型别名的扫描包
        factory.setTypeAliasesPackage("com.zhu.domain");
        return factory;
    }

    /**
     * 扫描mapper映射文件
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc=new MapperScannerConfigurer();
        msc.setBasePackage("com.zhu.dao");
        return msc;
    }

}
