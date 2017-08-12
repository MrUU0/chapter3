package org.smart4j.framework.utils;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 * @author WZ
 * @Class ArrayUtil
 * @create 2017-08-12
 **/
public final class ArrayUtil {

    /**
     * 判断 数组 是否为空
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }

    /**
     * 判断数组是否不为空
     * @param array
     * @return
     */
    public static boolean isNotEmpty(Object[] array){
        return !ArrayUtils.isEmpty(array);
    }
}
