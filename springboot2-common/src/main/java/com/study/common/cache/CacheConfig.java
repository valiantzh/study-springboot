package com.study.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * 缓存配置
 * @author valiantzh
 * @version 1.0
 */
@Configuration
@EnableCaching // 标注启动缓存
public class CacheConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    /**
     * ehcache 主要的管理器
     * @param bean
     * @return
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        LOGGER.debug(">>CacheConfig.ehCacheCacheManager()<<");
        return new EhCacheCacheManager(bean.getObject());
    }


    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        LOGGER.debug(">>CacheConfig.ehCacheManagerFactoryBean()<<");
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();

        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.setShared(true);

        return factoryBean;
    }

}
