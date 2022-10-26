package me.yifeiyuan.java8;

import java.util.Optional;
import java.util.function.Function;

public class OptionalAPI {


    public static void main(String[] args) {
        nonNullOptional();

        nullableOptional();
    }

    private static void nullableOptional() {
        Optional<String> nullable = Optional.ofNullable(null);

        nullable.get();
    }

    private static void nonNullOptional() {
        Optional<String> name = Optional.ofNullable("Fitz");
        //如果 value 不为 null，则执行
        name.ifPresent(v->{
            System.out.println("userName:"+v);
        });

        name.filter(v->v.startsWith("A")).ifPresent(r->{
            System.out.println("startsWith A"); //没有输出
        });

        //map 返回了一个新的 Optional ，不会改变原来的值
        name.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return 233;
            }
        }).ifPresent(v-> System.out.println(v.toString()));//233

        System.out.println(name.get());

        String flapMappedValue = name.flatMap(new Function<String, Optional<String>>() {
            @Override
            public Optional<String> apply(String s) {
                return Optional.of(s+"+flatMap");
            }
        }).get();
        System.out.println(flapMappedValue);//Fitz+flatMap
    }
}
