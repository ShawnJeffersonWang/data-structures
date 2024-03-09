package com.shawn.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode295_2 {

    public void addNum(int num) {
        if (left.size() == right.size()) {
            right.offer(num);
            left.offer(right.poll());
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }

    public double findMedian() {
        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return left.peek();
        }
    }

    // 大顶堆
    private PriorityQueue<Integer> left = new PriorityQueue<>(
            (a, b) -> Integer.compare(b, a)
    );

    // 默认创建是小顶堆
    private PriorityQueue<Integer> right = new PriorityQueue<>(
            (a, b) -> Integer.compare(a, b)
    );

    public static void main(String[] args) {
        // 小顶堆比较器 compare -1 a<b, 0 a==b, 1 a>b
//        Comparator<Integer> cmp = (a, b) -> Integer.compare(a, b);
        Comparator<Integer> cmp = (a, b) -> Integer.compare(b, a);
        // 父元素值
        int a = 10;
        // 新增元素值
        int b = 20;
        if (cmp.compare(a, b) > 0) {
            System.out.println("上浮");
        } else {
            System.out.println("停止上浮");
        }
    }
}
