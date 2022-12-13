package me.yifeiyuan.proxy;

//静态代理
public class StaticProxy {


    interface Greeting {

        void hello(String name);
    }

    static class GreetingProxy implements Greeting {
        Greeting target;

        public GreetingProxy(Greeting target) {
            this.target = target;
        }

        @Override
        public void hello(String name) {
            target.hello(name);
            System.out.println("来了老弟！~");
        }
    }

    public static void main(String[] args) {

        Greeting target = new Greeting() {
            @Override
            public void hello(String name) {
                System.out.println("Hello," + name);
            }
        };

        GreetingProxy proxy = new GreetingProxy(target);

        proxy.hello("程序亦非猿");
    }
}
