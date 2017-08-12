package org.smart4j.framework;

import org.smart4j.framework.helper.*;
import org.smart4j.framework.utils.ClassUtil;

/**
 * 加载相应的 Helper 类
 * @author WZ
 * @Class HelperLoader
 * @create 2017-08-12
 **/
public class HelperLoader {

    /**
     * 加载 helper 类
     */
    public static void init(){
        Class<?>[] classList = {
                //加载 basePackage 下的所有bean
                ClassHelper.class,
                //将 ClassHelper 加载到的 bean 实例化（单例）
                BeanHelper.class,
                //依赖注入，遍历BeanHelper的Map中所有Bean，注入有Inject注解的域
                IocHelper.class,
                //将 URL 与 method 的映射关系放入Map。遍历 ClassHelper 加载到的所有Controller，根据其方法的 Action 注解，进行关系对应
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
