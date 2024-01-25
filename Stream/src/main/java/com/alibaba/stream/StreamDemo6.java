package com.alibaba.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

public class StreamDemo6 {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "aa", "ab", "ac", "dd", "ee");

        list.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.startsWith("a");
            }
        }).forEach(s -> System.out.println(s));

        /*
        1. 中间方法，返回新的Stream流，原来的Stream流只能使用一次，建议使用链式编程
        2. 修改Stream流中的数据，不会影响原来集合或者数组中的数据
         */
        list.stream().filter(s -> s.startsWith("a")).forEach(s -> System.out.println(s));

        list.stream().limit(3)
                .forEach(s -> System.out.println(s));

        list.stream().skip(4)
                .forEach(s -> System.out.println(s));
    }
}
