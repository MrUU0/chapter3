package chapter4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author WZ
 * 2017-08-14
 * @name DynamicProxy
 **/
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object obj){
        this.target = obj;
    }


    public static void main(String[] args) {

       Hello helloProxy = new DynamicProxy( new HelloImpl()).getProxy();

        helloProxy.say();

    }

    /**
     * 获取一个代理对象
     * @param <T>
     * @return
     */
    public <T> T getProxy(){
        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }
    private void before(){
        System.out.println("before ... ");
    }
    private void after(){
        System.out.println("after ... ");
    }
}
