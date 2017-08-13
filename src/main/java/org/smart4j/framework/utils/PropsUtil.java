package org.smart4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created by wangz on 2017/8/8.
 */
public class PropsUtil {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * load properties by fileName
     * @param fileName
     * @return
     */
    public static Properties loadProps( String fileName){
        Properties props = null;
        InputStream is = null;
        try{
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if( is == null){
                throw new FileNotFoundException( fileName + " file not found");
            }
            props = new Properties();
            props.load(is);
        }catch( IOException e){
            logger.error(" load properties file failure", e);
        }finally{
            if( is != null){
                try{
                    is.close();
                }catch( IOException e){
                    logger.error("close input stream failure",e);
                }
            }
        }
        return props;
    }

    /**
     * get a String properties item
     * @param props
     * @param key
     * @return
     */
    public static String getString( Properties props, String key){
        return getString( props, key, "");
    }

    /**
     * get a String properties item, if props does not contions that key , return a default value
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString( Properties props, String key, String defaultValue){
        if( props.containsKey(key))
            return props.getProperty(key);
        return defaultValue;
    }

    /**
     * get int value by key
     * @param props
     * @param key
     * @return
     */
    public static int getInt( Properties props, String key){
        return  getInt( props, key, 0);
    }

    /**
     * get int value by key, if the props does contions the key , return default value
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt( Properties props, String key, int defaultValue){
        if (props.contains(key))
            return CastUtil.castInt( props.getProperty(key));
        return defaultValue;
    }

    /**
     * get a boolean value by key from props
     * @param props
     * @param key
     * @return
     */
    public static boolean getBoolean( Properties props, String key){
        return getBoolean( props, key, false);
    }

    /**
     * get booelan value, if the props does not contains the key, then return default value
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean( Properties props, String key, boolean defaultValue){
        if( props.contains(key))
            return CastUtil.castBoolean( props.getProperty(key));
        return defaultValue;
    }

}
