package io.github.iamtwang.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Tao
 */
public class BeforeAdvice implements InvocationHandler {

    private Object bean;
    private MethodInvocation invocation;

    public BeforeAdvice(Object bean, MethodInvocation invocation){
        this.bean = bean;
        this.invocation = invocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        invocation.invoke();
        return method.invoke(bean, args);
    }
}
