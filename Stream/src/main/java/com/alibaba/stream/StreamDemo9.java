package com.alibaba.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.IntFunction;

public class StreamDemo9 {

    public static void main(String[] args) {

        ArrayList<String> list=new ArrayList<>();
        Collections.addAll(list,"a","b","c","d","e");
        
        list.stream().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        long count = list.stream().count();
        System.out.println(count);

        String[] arr = list.stream().toArray(new IntFunction<String[]>() {
            @Override
            public String[] apply(int value) {
                return new String[value];
            }
        });

        System.out.println(Arrays.toString(arr));

        String[] arr2 = list.stream().toArray(value -> new String[value]);

        System.out.println(Arrays.toString(arr2));
    }
}
