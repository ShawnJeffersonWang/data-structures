package com.shawn.algorithm;

import com.shawn.datastructure.MinHeap;

public class LeetCode703 {

    // 构造方法和add方法都会用到小顶堆，所以把小顶堆作为这个类的成员变量
    private MinHeap heap;

    public LeetCode703(int k, int[] nums) {
        heap = new MinHeap(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (!heap.isFull()) {
            heap.offer(val);
        } else if (val > heap.peek()) {
            heap.replace(val);
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        LeetCode703 test = new LeetCode703(3, new int[]{4, 5, 8, 2});

        // 小顶堆

        // 4
        System.out.println(test.add(3));
        // 5
        System.out.println(test.add(5));
        // 5
        System.out.println(test.add(10));
        // 8
        System.out.println(test.add(9));
        // 8
        System.out.println(test.add(4));
    }
}
