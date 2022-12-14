package me.yifeiyuan.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 动态代理
 */
public class CGLIBDynamicProxy {

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
            //调用原来 proxy 逻辑
//            method.invoke(target, args);
            proxy.invokeSuper(obj, args);
            //增加扩展能力
            if ("hello".equals(method.getName())) {
                System.out.println("来了老弟！~");
            }
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

        //可以生成 GreetingImpl 实现类
        GreetingImpl proxy2 = (GreetingImpl) cglibProxy.createProxy();
        proxy2.hello("程序亦非猿");
    }
}
