package io.github.iamtwang.aop.nolib;

import io.github.iamtwang.aop.services.HelloService;
import io.github.iamtwang.aop.services.HelloServiceImpl;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Tao
 */
public class ToyAOPTest {

    @Test
    public void createProxy() {

        MethodInvocation logTask = () -> System.out.println("log task start");

        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

        InvocationHandler beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);

        HelloService helloServiceImplProxy = (HelloService) ToyAOP.createProxy(helloServiceImpl,beforeAdvice);

        String result = helloServiceImplProxy.hello("Tao");

        assertThat(result, is("Hello Tao"));

    }
}