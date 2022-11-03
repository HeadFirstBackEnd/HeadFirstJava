package me.yifeiyuan.java6;

import java.util.ServiceLoader;

/**
 * No language changes were introduced in Java SE 6.
 */
public class SpiAPI {

    public static void main(String[] args) {
        ServiceLoader<SomeService> services = ServiceLoader.load(SomeService.class);

        for (SomeService service : services) {
            service.printLog();
        }
    }
}
