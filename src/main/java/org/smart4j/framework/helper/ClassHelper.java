package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.utils.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 类操作 助手类
 * @author WZ
 * @Class ClassHelper
 * @create 2017-08-11
 **/
public final class ClassHelper {

    private static final Set<Class<?>> CLASS_SET;

    static{
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage)  ;
    }

    /**
     * 获取应用包下的的所有类
     * @return
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取应用包名下的所有service类
     * @return
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(Service.class))
                classSet.add(cls);
        }
        return classSet;
    }

    /**
     * 获取应用包下所有controller
     * @return
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class))
                classSet.add(cls);
        }
        return classSet;
    }

    /**
     * 获取应用包下的所有备案类
     * @return
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        classSet.addAll( getControllerClassSet());
        classSet.addAll( getServiceClassSet());
        return classSet;
    }


}
