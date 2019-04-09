package com.study.common.mybatis;


import com.study.common.util.StringUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author valiantzh
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisConfig.class);

    //  配置类型别名
    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;

    //  配置mapper的扫描，找到所有的mapper.xml映射文件
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    //  加载全局的配置文件
    @Value("${mybatis.configLocation}")
    private String configLocation;

    @Autowired
    private DataSource dataSource;
    // DataSource配置
//  @Bean
//  @ConfigurationProperties(prefix = "spring.datasource")
//  public DruidDataSource dataSource() {
//      return new com.alibaba.druid.pool.DruidDataSource();
//  }


    // 提供SqlSeesion
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        LOGGER.debug(">>MybatisConfig.sqlSessionFactoryBean<<");
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);

            // 读取配置
            sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);

            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(mapperLocations);
            sessionFactoryBean.setMapperLocations(resources);
            //pagehelper-spring-boot-starter 动配置分页插件的功能
            if(StringUtil.isNotBlank(configLocation)){
                sessionFactoryBean.setConfigLocation(
                        new DefaultResourceLoader().getResource(configLocation));
            }

            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            LOGGER.warn("mybatis resolver mapper*xml is error");
            return null;
        } catch (Exception e) {
            LOGGER.warn("mybatis sqlSessionFactoryBean create error");
            return null;
        }
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        LOGGER.debug(">>MybatisConfig.annotationDrivenTransactionManager<<");
        return new DataSourceTransactionManager(dataSource);
    }

}
