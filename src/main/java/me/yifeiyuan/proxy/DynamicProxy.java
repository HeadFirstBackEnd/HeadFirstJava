package me.yifeiyuan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Dynamic Proxy 动态代理
 */
public class DynamicProxy {


    interface Greeting {
        void hello(String name);
    }

    public static void main(String[] args) {

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("hello".equals(method.getName())) {
                    System.out.println("Hello, " + args[0]);
                }
                return null;
            }
        };

        Greeting greeting = (Greeting) Proxy.newProxyInstance(Greeting.class.getClassLoader(), new Class[]{Greeting.class}, handler);

        greeting.hello("程序亦非猿");
    }
}
