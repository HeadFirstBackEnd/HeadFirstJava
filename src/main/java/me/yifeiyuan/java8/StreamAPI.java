package me.yifeiyuan.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamAPI {


    public static List<Integer> integerList = new ArrayList<>();

    static {
        for (int i = 0; i < 20; i++) {
            integerList.add(i);
        }
    }
    public static void main(String[] args) {
        listStream();
    }

    private static void listStream(){
        List<Integer> newList = integerList.stream()
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        return integer % 2 == 0;
                    }
                }).collect(Collectors.toList());

        for (Integer integer : newList) {
            System.out.println(integer);
        }
    }
}
