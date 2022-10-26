package me.yifeiyuan.java8;

public class FunctionalInterfaceAPI {



    @FunctionalInterface
    interface FunctionalInterfaceA{

        void run();

//        void execute();

        default void apply(){
            System.out.println("default apply");
        }
    }

    interface FunctionalInterfaceB{

        void run();

        void execute();

    }

    public static void main(String[] args) {

        testFunctionalInterfaceA(new FunctionalInterfaceA() {
            @Override
            public void run() {
                System.out.println("new 匿名内部类");
            }
        });

        testFunctionalInterfaceA(()-> System.out.println("转成 Lambda 写法"));

        testFunctionalInterfaceB(new FunctionalInterfaceB() {
            @Override
            public void run() {

            }

            @Override
            public void execute() {

            }
        });
    }

    private static void testFunctionalInterfaceA(FunctionalInterfaceA interfaceA) {
        interfaceA.run();
    }

    private static void testFunctionalInterfaceB(FunctionalInterfaceB func) {
        func.run();
    }
}
