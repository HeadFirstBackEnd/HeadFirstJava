package me.yifeiyuan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Dynamic Proxy 动态代理
 * Proxy.newProxyInstance 只能作用于接口
 */
public class JDKDynamicProxy {


    interface Greeting {
        void hello(String name);
    }

    static class GreetingImpl implements Greeting {
        @Override
        public void hello(String name) {
            System.out.println("Hello," + name);
        }
    }

    public static void main(String[] args) {

//        normalUsecase1();
//        normalUsecase2();

//        testDynamicProxy();
        //抛异常用例
//        testDynamicProxy2();

        testDynamicProxyImpl();
    }

    private static void normalUsecase1() {
        Greeting greeting = new GreetingImpl();
        greeting.hello("程序亦非猿");
    }

    private static void normalUsecase2() {
        Greeting greeting = new Greeting() {
            @Override
            public void hello(String name) {
                System.out.println("Hello," + name);
            }
        };

        greeting.hello("程序亦非猿");
    }

    static class GreetingProxy implements InvocationHandler {

        Object greeting;

        public GreetingProxy(Object greeting) {
            this.greeting = greeting;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("hello".equals(method.getName())) {
                method.invoke(greeting, args);
                System.out.println("来了老弟！~");
            }
            return null;
        }
    }

    private static void testDynamicProxy() {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("hello".equals(method.getName())) {
                    System.out.println("Hello," + args[0]);
                }
                return null;
            }
        };

        Greeting greeting = (Greeting) Proxy.newProxyInstance(Greeting.class.getClassLoader(), new Class[]{Greeting.class}, handler);

        greeting.hello("程序亦非猿");
    }

    private static void testDynamicProxyImpl() {

        GreetingProxy proxy = new GreetingProxy(new GreetingImpl());

        Greeting greeting = (Greeting) Proxy.newProxyInstance(Greeting.class.getClassLoader(), new Class[]{Greeting.class}, proxy);

        greeting.hello("程序亦非猿");
    }

    //me.yifeiyuan.proxy.JDKDynamicProxy$GreetingImpl is not an interface
//    private static void testDynamicProxy2() {
//        InvocationHandler handler = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                if ("hello".equals(method.getName())) {
//                    System.out.println("来了老弟," + args[0]);
//                }
//                return null;
//            }
//        };
//        //不可以使用具体类
//        Greeting greeting = (Greeting) Proxy.newProxyInstance(Greeting.class.getClassLoader(), new Class[]{GreetingImpl.class}, handler);
//
//        greeting.hello("程序亦非猿");
//    }
}
