package com.study.upms.dao;

import com.study.common.util.MybatisGeneratorUtil;
import com.study.common.util.PropertiesFileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成类
 * @author valiantzh
 * @version 1.0
 */
public class Generator {

    // 根据命名规范，只修改此常量值即可
    private final static String MODULE = "springboot2-upms-core";
    private final static String SUBMODULE_PREFIX = "";//
    private final static String DATABASE = "biu";
    private final static String TABLE_PREFIX = "sys_";
    private final static String PACKAGE_NAME = "com.study.upms";
    private final static boolean isGenService = true;
    private final static boolean isGenMock    = false;
    private final static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
    private final static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
    private final static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
    private final static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");
    // 需要insert后返回主键的表配置，key:表名,value:主键名
    private static Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<>();
    static {
        //LAST_INSERT_ID_TABLES.put("upms_user", "user_id");
    }

    /**
     * 自动代码生成
     * @param args
     */
    public static void main(String[] args) throws Exception {
        MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, MODULE, SUBMODULE_PREFIX,DATABASE, TABLE_PREFIX, PACKAGE_NAME, isGenService,isGenMock, LAST_INSERT_ID_TABLES);
    }

}
