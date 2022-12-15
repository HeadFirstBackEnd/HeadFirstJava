package me.yifeiyuan.concurrent;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

/**
 *
 * 线程池
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {

        int corePoolSize = 5;
        int maximumPoolSize = 128;
        long keepAliveTime = 3_000;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(128);

        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                return thread;
            }
        };

        MyThreadPool myThreadPool = new MyThreadPool(corePoolSize, maximumPoolSize, keepAliveTime, unit, blockingQueue,threadFactory);

        for (int i = 0; i < 100; i++) {
            final int value = i;
            myThreadPool.submit(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(100);
                        System.out.println("name="+Thread.currentThread().getName()+",value="+value);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }


    static class MyThreadPool extends ThreadPoolExecutor {

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
        }
    }
}

