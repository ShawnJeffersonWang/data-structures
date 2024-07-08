package com.shawn.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class StreamDemo7 {

    public static void main(String[] args) {

        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "a", "a", "b", "b", "b", "c", "d");

        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "a", "a", "b", "b", "b", "c", "d");

        list1.stream().distinct().forEach(s -> System.out.println(s));

        System.out.println("===================");
        Stream.concat(list1.stream(), list2.stream()).forEach(s -> System.out.println(s));


    }
}
