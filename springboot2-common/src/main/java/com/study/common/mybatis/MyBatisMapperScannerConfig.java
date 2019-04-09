package com.study.common.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author valiantzh
 * @version 1.0
 */
@Configuration
@AutoConfigureAfter({MybatisConfig.class})
public class MyBatisMapperScannerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisMapperScannerConfig.class);
    //  扫描包路径
    @Value("${mybatis.mapperScannerBasePackage}")
    private String mapperScannerBasePackage;
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        LOGGER.debug(">>MyBatisMapperScannerConfig.mapperScannerConfigurer:{}<<",mapperScannerBasePackage);
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.**.dao.*.mapper");

        return mapperScannerConfigurer;
    }

}
