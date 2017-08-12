package org.smart4j.framework.helper;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.utils.PropsUtil;

import java.util.Properties;

/**
 * @author WZ
 * @Class ConfigHelper
 * @create 2017-08-10
 **/
public class ConfigHelper {

    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 获取 jdbc 驱动
     * @return
     */
    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 获取 jdbc URL
     * @return
     */
    public static String getJdbcUrl(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    /**
     * 获取 jdbc 用户名
     * @return
     */
    public static String getJdbcUsername(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    /**
     * 获取 应用 基础包名
     * @return
     */
    public static String getJdbcPassword(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * 获取应用 JSP 路径
     * @return
     */
    public static String getAppBasePackage(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 获取应用静态资源路径
     * @return
     */
    public static String getAppJspPath(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }

    public static String getAppAssetPath(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }
}
