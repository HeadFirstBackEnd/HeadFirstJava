package me.yifeiyuan.sdk.lombok;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

public class LombokDemo {

    @Data(staticConstructor = "of")
    static class FooB {
        private String a;
        private Integer b;
    }

    @Builder()
    @Data
    static class FooC {
        private String a;
        private Integer b;
    }

    public static void main(String[] args) {

        getterSetter();

        testData();

        //静态工厂
        FooB fooB = FooB.of();

        //Builder 模式
        FooC fooC = new FooC.FooCBuilder().a("A").b(1).build();

    }

    //只生成 getter setter
    static class FooGetterSetter {
        @Getter
        @Setter
        private String a;

        @Setter(lombok.AccessLevel.PUBLIC)
        private Integer b;
    }

    private static void getterSetter() {
        FooGetterSetter getterSetter = new FooGetterSetter();
        getterSetter.setA("A");
        getterSetter.getA();

        getterSetter.setB(1);
    }


    @Data
    static class Foo {
        private String a;
        private Integer b;
    }
    private static void testData() {
        Foo foo = new Foo();
        foo.setA("A");
        foo.getA();
        foo.setB(1);
        foo.getB();
    }

    @Slf4j(topic = "Slf4j-Lombok")
    @ToString
    static class Slf4jDemo{
        public static void logTest(){
        }
    }
    private static void testSlf4j() {

    }
}
