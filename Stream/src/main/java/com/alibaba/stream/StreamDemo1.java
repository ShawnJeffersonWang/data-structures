package com.alibaba.stream;

import java.util.ArrayList;
import java.util.Collections;

/*
Stream流的作用：结合了Lambda表达式，简化集合，数组的操作

Stream流的使用步骤：
1. 先得到一条Stream流（流水线），并把数据放上去
2. 利用Stream流中的API进行各种操作

过滤 转换 -> 中间方法：方法调用完毕之后，还可以调用其他方法
统计 打印 -> 终结方法：最后一步，调用完毕之后，不能调用其他方法

 */
public class StreamDemo1 {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "b", "c", "d", "e");

//        Stream<String> stream1 = list.stream();
//        stream1.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });

        list.stream().forEach(s -> System.out.println(s));
    }
}
