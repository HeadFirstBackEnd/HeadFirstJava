package me.yifeiyuan.concurrent;

import java.util.function.Supplier;

/**
 *
 * ThreadLocal
 */
public class ThreadLocalDemo {

    static ThreadLocal<Integer> sIntegerThreadLocal = new ThreadLocal<>();

    static ThreadLocal<Integer> sIntegerThreadLocal2 = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return 123;
        }
    };
    static ThreadLocal<Integer> sIntegerThreadLocal3 = ThreadLocal.withInitial(new Supplier<Integer>() {
        @Override
        public Integer get() {
            return 111;
        }
    });

    //lambda
    static ThreadLocal<Integer> sIntegerThreadLocal4 = ThreadLocal.withInitial(() -> 666);

    public static void main(String[] args) {

        testThreadLocalAPI();
    }

    //基础 API
    private static void testThreadLocalAPI() {
        System.out.println(sIntegerThreadLocal.get());//null
        sIntegerThreadLocal.set(2323);
        System.out.println(sIntegerThreadLocal.get());//2323
        sIntegerThreadLocal.remove();
        System.out.println(sIntegerThreadLocal.get());//null

        System.out.println(sIntegerThreadLocal2.get());//123

        System.out.println(sIntegerThreadLocal3.get());//111

        sIntegerThreadLocal3.set(222);
        System.out.println(sIntegerThreadLocal3.get());//222

        new Thread(){
            @Override
            public void run() {
                super.run();
                System.out.println(this.getName());//Thread-0
                System.out.println(sIntegerThreadLocal3.get());//111
                System.out.println(sIntegerThreadLocal.get());//null
            }
        }.start();
    }
}
