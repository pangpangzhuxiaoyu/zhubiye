package com.zhu.config;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;


import javax.sql.DataSource;
import org.apache.ibatis.session.Configuration;

public class MybaitsConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean factory =new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        // 配置 MyBatis 全局配置
        Configuration configuration = new Configuration();
        configuration.setLogImpl(StdOutImpl.class);  // 设置 MyBatis 日志打印方式
        factory.setConfiguration(configuration);
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
