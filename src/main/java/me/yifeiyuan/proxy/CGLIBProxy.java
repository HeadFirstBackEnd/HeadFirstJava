package me.yifeiyuan.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLIBProxy {

    interface Greeting {
        void hello(String name);
    }

    static class GreetingImpl implements Greeting {
        @Override
        public void hello(String name) {
            System.out.println("Hello," + name);
        }
    }

    static class CGLIBProxyImpl implements MethodInterceptor {

        Enhancer enhancer = new Enhancer();

        Object target;

        public CGLIBProxyImpl(Object target) {
            this.target = target;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            method.invoke(target, args);
            return null;
        }

        public Object createProxy() {
            enhancer.setSuperclass(target.getClass());
            enhancer.setCallback(this);
            return enhancer.create();
        }
    }

    public static void main(String[] args) {

        Greeting target = new GreetingImpl();

        CGLIBProxyImpl cglibProxy = new CGLIBProxyImpl(target);

        Greeting proxy = (Greeting) cglibProxy.createProxy();
        proxy.hello("程序亦非猿");
    }
}
