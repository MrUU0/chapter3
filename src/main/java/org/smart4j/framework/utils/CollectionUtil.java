package org.smart4j.framework.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by wangz on 2017/8/8.
 */
public final class CollectionUtil {

    /**
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return ! CollectionUtils.isEmpty(collection);
    }

    /**
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?,?> map){
        return MapUtils.isEmpty(map);
    }

    /**
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?,?> map){
        return ! MapUtils.isEmpty(map);
    }

}
