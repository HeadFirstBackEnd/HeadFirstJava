package me.yifeiyuan.java7;

import java.io.FileOutputStream;

public class TryWithResources {


    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("/a.txt")) {
//            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
