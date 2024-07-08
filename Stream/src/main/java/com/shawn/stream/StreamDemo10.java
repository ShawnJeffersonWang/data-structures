package com.shawn.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamDemo10 {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a-m-15", "b-f-18", "c-f-17", "d-m-20", "e-f-21");

        List<String> newList = list.stream()
                .filter(s -> "m".equals(s.split("-")[1]))
                .collect(Collectors.toList());

        System.out.println(newList);

        Set<String> newSet = list.stream()
                .filter(s -> "m".equals(s.split("-")[1]))
                .collect(Collectors.toSet());

        System.out.println(newSet);

        System.out.println("======================");
        Map<String, Integer> map = list.stream().filter(s -> "m".equals(s.split("-")[1]))
                .collect(Collectors.toMap(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.split("-")[0];
                    }
                }, new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s.split("-")[2]);
                    }
                }));
        System.out.println(map);

        System.out.println("=======================");
        Map<String, Integer> map2 = list.stream()
                .filter(s -> "m".equals(s.split("-")[1]))
                .collect(Collectors.toMap(
                        s -> s.split("-")[0],
                        s -> Integer.parseInt(s.split("-")[2])
                ));
        System.out.println(map2);
    }
}
