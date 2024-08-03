package com.shawn.stream;

import java.util.Arrays;

public class StreamDemo3 {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        String[] arr2 = {"a", "b", "c"};
        Arrays.stream(arr).forEach(s -> System.out.println(s));

        Arrays.stream(arr2).forEach(s -> System.out.println(s));
    }
}
