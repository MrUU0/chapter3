package chapter4;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用CGlib实现的动态代理
 * @author WZ
 * 2017-08-14
 * @name CGlibProxy
 **/
public class CGlibProxy implements MethodInterceptor {
    /**
     * 单例模式
     */
    private static final CGlibProxy proxy = new CGlibProxy();
    private CGlibProxy(){};
    public static CGlibProxy getInstance(){
        return proxy;
    }

    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object res = methodProxy.invokeSuper(o, objects);
        after();
        return res;
    }

    public static void main(String[] args) {
        Hello helloProxy = CGlibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say();
    }
    private void before(){
        System.out.println("before ... ");
    }
    private void after(){
        System.out.println("after ...");
    }
}
