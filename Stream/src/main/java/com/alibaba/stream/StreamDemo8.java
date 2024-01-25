package com.alibaba.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

public class StreamDemo8 {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "wang-11", "zhang-22", "li-33");

        list.stream().map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                String[] arr = s.split("-");
                return Integer.parseInt(arr[1]);
            }
        }).forEach(s -> System.out.println(s));

        list.stream()
                .map(s->Integer.parseInt(s.split("-")[1]))
                .forEach(s-> System.out.println(s));
    }
}
