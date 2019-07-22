package io.github.iamtwang.aop.nolib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Tao
 */
public class ToyAOP {

    public static Object createProxy(Object bean, InvocationHandler handler) {
        return Proxy.newProxyInstance(ToyAOP.class.getClassLoader(), bean.getClass().getInterfaces(), handler);
    }
}
