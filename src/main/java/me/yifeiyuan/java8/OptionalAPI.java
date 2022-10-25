package me.yifeiyuan.java8;

import java.util.Optional;
import java.util.function.Function;

public class OptionalAPI {


    public static void main(String[] args) {
        Optional<String> name = Optional.ofNullable("Fitz");
        //如果 value 不为 null，则执行
        name.ifPresent(v->{
            System.out.println("userName:"+v);
        });

        name.filter(v->v.startsWith("A")).ifPresent(r->{
            System.out.println("startsWith A");
        });

        name.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return 233;
            }
        }).ifPresent(v-> System.out.println(v.toString()));
    }
}