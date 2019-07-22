package io.github.iamtwang.aop;

import io.github.iamtwang.aop.services.GreetingService;
import io.github.iamtwang.aop.services.HelloService;
import io.github.iamtwang.aop.services.HelloServiceImpl;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Tao
 */
public class ProxyFactoryTest {

    private MethodInvocation logTask = () -> System.out.println("log task start");

    @Test
    public void createJdkProxy() {

        HelloService helloService = new HelloServiceImpl();

        InvocationHandler beforeAdvice = new BeforeAdvice(helloService, logTask);

        HelloService helloServiceImplProxy = (HelloService) ProxyFactory.createJdkProxy(helloService,beforeAdvice);

        String result = helloServiceImplProxy.hello("Tao");

        assertThat(result, is("Hello Tao"));
    }

    @Test
    public void createCglibProxy() {

        GreetingService greetingService = new GreetingService();

        GreetingService greetingServiceProxy = (GreetingService) ProxyFactory.createCglibProxy(greetingService.getClass(), logTask);

        String result = greetingServiceProxy.hello("Tao");

        assertThat(result, is("Hello Tao"));
    }
}