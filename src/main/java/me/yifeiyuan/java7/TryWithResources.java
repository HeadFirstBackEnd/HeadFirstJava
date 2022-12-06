package me.yifeiyuan.java7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class TryWithResources {


    public static void main(String[] args) {
        tryCatchFinally();
        tryWithResources();
    }

    private static void tryWithResources() {
        System.out.println("tryWithResources called");
        try (Scanner scanner = new Scanner(Objects.requireNonNull(TryWithResources.class.getResourceAsStream("/test.txt")))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // FIXME: 2022/12/5 
    private static void tryCatchFinally() {
        System.out.println("tryCatchFinally called");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("/test.txt"));
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
