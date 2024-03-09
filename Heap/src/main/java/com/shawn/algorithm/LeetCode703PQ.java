package com.shawn.algorithm;

import java.util.PriorityQueue;

public class LeetCode703PQ {
    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{3, 4, 5, 8, 2});
        // 小顶堆

        // 4
        System.out.println(kthLargest.add(3));
        // 5
        System.out.println(kthLargest.add(5));
        // 5
        System.out.println(kthLargest.add(10));
        // 8
        System.out.println(kthLargest.add(9));
        // 8
        System.out.println(kthLargest.add(4));
    }
}

class KthLargest {
    PriorityQueue<Integer> pq;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<Integer>(k);
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */