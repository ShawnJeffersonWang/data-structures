package com.shawn.algorithm;

import com.shawn.datastructure.MinHeap;

public class LeetCode215 {

    public int findKthLargest(int[] numbers, int k) {
        MinHeap heap = new MinHeap(k);
        for (int i = 0; i < k; i++) {
            heap.offer(numbers[i]);
        }

        for (int i = k; i < numbers.length; i++) {
            if (numbers[i] > heap.peek()) {
                heap.replace(numbers[i]);
            }
        }

        return heap.peek();
    }

    public static void main(String[] args) {
        // 5
        System.out.println(new LeetCode215().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        // 4
        System.out.println(new LeetCode215().findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}
