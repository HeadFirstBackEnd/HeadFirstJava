package me.yifeiyuan.java8;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {


    public static List<Integer> integerList = new ArrayList<>();
    public static List<String> stringList = new ArrayList<>();

    static {
        for (int i = 0; i < 20; i++) {
            integerList.add(i);
        }
        stringList.add("A");
        stringList.add("Aa");
        stringList.add("Aaa");
        stringList.add("1");
        stringList.add("B");
        stringList.add("Bb");
        stringList.add("Bbb");
        stringList.add("Bbb");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("程序");
        stringList.add("程序亦非猿");
        stringList.add("Fitz");
    }

    public static void main(String[] args) {

        reduce();

        streamCreation();

        anyMatchAPI();
        allMatchAPI();

        listStream();

        terminalOperations();

        parallelStream();
    }

    private static void reduce() {
        List<Integer> i = new ArrayList<>();
        i.add(1);
        i.add(3);
        i.add(2);
        i.add(1);

       int sum = i.stream().reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).get();

        System.out.println("sum ="+sum);
    }

    private static void parallelStream() {
        System.out.println("--parallelStream--");

        int poolSize = ForkJoinPool.commonPool().getPoolSize();

        System.out.println("poolSize=" + poolSize);//0 ?

        stringList.parallelStream();
    }

    private static void terminalOperations() {
        System.out.println("--terminalOperations--");

        //Collectors.toList()
        List<String> startsWithAList = stringList.stream()
                .filter(v -> v.startsWith("A"))
                .collect(Collectors.toList());

        System.out.println(startsWithAList); //[A, Aa, Aaa]

        //Collectors.toMap   HashMap
        Map<String, String> stringMap = stringList.stream()
                .distinct() //去重
                .limit(3)//取 3 个
                .collect(Collectors.toMap(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return "key:" + s;
                    }
                }, new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return "value:" + s;
                    }
                }));

        System.out.println(stringMap);
    }

    private static void streamCreation() {
        Stream.empty();
        Stream.of(stringList);

        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();

        Stream<String> streamOfArray = Stream.of("a", "b", "c");
    }

    private static void anyMatchAPI() {
        boolean anyMatch = stringList
                .stream()
                .anyMatch(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.startsWith("Aa");
                    }
                });

        System.out.println("anyMatch:" + anyMatch);
    }

    private static void allMatchAPI() {
        boolean allMatch = stringList
                .stream()
                .allMatch(Objects::nonNull);

        System.out.println("allMatch:" + allMatch);

        allMatch = Collections.emptyList().stream().allMatch(Objects::nonNull);

        System.out.println("allMatch:" + allMatch);
    }

    private static void listStream() {
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
