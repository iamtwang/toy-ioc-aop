package io.github.iamtwang.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Tao
 */
public class ProxyFactory {

    public static Object createJdkProxy(Object bean, InvocationHandler handler) {
        return Proxy.newProxyInstance(
                ProxyFactory.class.getClassLoader(),
                bean.getClass().getInterfaces(),
                handler);
    }

    public static Object createCglibProxy(Class target, MethodInvocation advice) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            advice.invoke();
            return proxy.invokeSuper(obj, args);
        });
        return enhancer.create();
    }
}
