package org.smart4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * @author WZ
 * @Class ReflectionUtil
 * @create 2017-08-11
 **/
public class ReflectionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls){
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            logger.error("new instance failure, class : " + cls, e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Object... args){
        try {
            method.setAccessible(true);
            return method.invoke(obj, args);
        } catch (Exception e) {
            logger.error("invoke method failure, method："+method, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置成员变量的值
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value){
        try{
             field.setAccessible(true);
             field.set(obj, value);
        }catch (Exception e){
            logger.error("set field value failure, field：" + field, e);
            throw new RuntimeException(e);
        }
    }

}
