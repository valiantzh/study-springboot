package com.study.common.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源配置
 * @author valiantzh
 * @version 1.0
 */
@Configuration
public class DataSourceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    ///////////////// Master /////////////////
    @Value("${spring.datasource.master.url}")
    private String urlMaster;

    @Value("${spring.datasource.master.username}")
    private String usernameMaster;

    @Value("${spring.datasource.master.password}")
    private String passwordMaster;

    @Value("${spring.datasource.master.driver-class-name}")
    private String driverClassNameMaster;

    ///////////////// Slave /////////////////
    @Value("${spring.datasource.slave.url}")
    private String urlSlave;

    @Value("${spring.datasource.slave.username}")
    private String usernameSlave;

    @Value("${spring.datasource.slave.password}")
    private String passwordSlave;

    @Value("${spring.datasource.slave.driver-class-name}")
    private String driverClassNameSlave;

    //////////////
    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.druid.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.druid.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.druid.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.druid.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.druid.filters}")
    private String filters;

    @Value("{spring.datasource.druid.connectionProperties}")
    private String connectionProperties;

    private DataSource create(String url, String username,String password, String driverClassName){
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        // configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            LOGGER.warn(">>filters={},error {}<<",filters,e.getMessage());
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }
    /**
     * 主库数据源
     *@return DataSource
     */
    @Bean(name = "masterDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        LOGGER.debug(">>DataSourceConfig.masterDataSource() {}<<",urlMaster);
        //return DataSourceBuilder.create().build();
        return create(urlMaster,usernameMaster,passwordMaster,driverClassNameMaster);
    }

    /**
     * 从库数据源
     *@return DataSource
     */
    @Bean(name = "slaveDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        LOGGER.debug(">>DataSourceConfig.slaveDataSource() {}<<", urlSlave);
        //return DataSourceBuilder.create().build();
        return create(urlSlave,usernameSlave, passwordSlave, driverClassNameSlave);
    }

    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean(name = "dataSource")
    @Primary
    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                        @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        LOGGER.debug(">>DataSourceConfig.dataSource()<<");
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.MASTER, masterDataSource);
        targetDataSources.put(DataSourceEnum.SLAVE, slaveDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(masterDataSource);// 默认的datasource

        return dataSource;
    }
}
