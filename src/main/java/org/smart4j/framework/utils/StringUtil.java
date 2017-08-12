package org.smart4j.framework.utils;

/**
 * Created by wangz on 2017/8/8.
 */
public final class StringUtil {

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

    /**
     * 将 字符串 按分割符分割
     * @param str
     * @param separator
     * @return
     */
    public static String[] splitString(String str, String separator){
        return str == null ? null : str.split(separator);
    }
}
