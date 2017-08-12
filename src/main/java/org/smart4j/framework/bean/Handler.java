package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装 Action 信息
 * @author WZ
 * @Class Handler
 * @create 2017-08-12
 **/
public class Handler {

    /**
     * Controller 类
     */
    private Class<?> controllerClass;
    /**
     * Action 方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod){
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass(){
        return this.controllerClass;
    }

    public Method getActionMethod(){
        return this.actionMethod;
    }
}
