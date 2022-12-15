package me.yifeiyuan.concurrent;

import com.sun.istack.internal.NotNull;

public class ThreadDemo {


    public static void main(String[] args) {

        dumpThread(Thread.currentThread());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);//Exception in thread "Thread-0" java.lang.RuntimeException: java.lang.InterruptedException: sleep interrupted
                }
            }
        });
        dumpThread(thread);
        thread.start();
//        thread.interrupt();
    }

    public static void dumpThread(@NotNull Thread thread) {
        StringBuilder builder = new StringBuilder();

        ThreadGroup threadGroup = thread.getThreadGroup();

        builder.append("threadGroup name=" + threadGroup.getName())
                .append("\n")
                .append("activeGroupCount="+threadGroup.activeGroupCount())
                .append("\n");

        String threadName = thread.getName();
        builder.append("threadName=" + threadName + "\n");

        System.out.println(builder.toString());
    }
}
