package org.smart4j.framework.utils;

/**
 * Created by wangz on 2017/8/8.
 */
public class StringUtil {

    /**
     * @param str
     * @return
     */
    public static boolean isEmpty( String str){
        return str == null || str.length() == 0;
    }

    /**
     * @param st
     * @return
     */
    public static boolean isNotEmpty(String str){
        return str != null || str.length() != 0;
    }

}
