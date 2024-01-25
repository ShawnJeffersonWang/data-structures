package com.alibaba.stream;

import java.util.stream.Stream;

public class StreamDemo4 {

    public static void main(String[] args) {

        Stream.of(1, 2, 3, 4, 5).forEach(s -> System.out.println(s));

        // Stream接口中静态方法of的细节
        // 方法的形参是一个可变参数，可以传递一堆零散的数据，也可以传递数组
        // 但是数组必须是引用数组类型，如果传递基本数据类型，是会把整个数组当做一个元素，放到stream中
        Stream.of("1", "2", "3", "4", "5").forEach(s -> System.out.println(s));
    }
}
