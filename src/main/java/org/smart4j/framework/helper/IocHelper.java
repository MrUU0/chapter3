package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.utils.ArrayUtil;
import org.smart4j.framework.utils.CollectionUtil;
import org.smart4j.framework.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 * @author WZ
 * @Class IocHelper
 * @create 2017-08-12
 **/
public class IocHelper {

    static {
        //获取所有Bean类与Bean实例之间的映射关系
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            //遍历 bean Map
            for(Map.Entry<Class<?>, Object> beanEntity : beanMap.entrySet()){
                Class<?> beanClass = beanEntity.getKey();
                Object beanInstance = beanEntity.getValue();
                //获取 bean 类定义的所有成员变量
                Field[] fields = beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(fields)){
                    //遍历fields
                    for(Field field : fields){
                        //判断当前bean field 是否带哟Inject注解
                        if(field.isAnnotationPresent(Inject.class)){
                            //在beanMap中获取 Bean Feild 对应的实例
                            Class<?> beanFieldClass = field.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if( beanFieldInstance != null){
                                //通过反射初始化beanField值
                                ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
