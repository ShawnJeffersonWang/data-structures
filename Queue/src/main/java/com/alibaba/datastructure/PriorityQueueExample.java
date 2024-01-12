package com.alibaba.datastructure;

import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // 创建优先队列
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // 插入元素
        priorityQueue.offer(8);
        priorityQueue.offer(3);
        priorityQueue.offer(5);
        priorityQueue.offer(2);
        priorityQueue.offer(10);

        // 按优先级删除元素
        while (!priorityQueue.isEmpty()) {
            int element = priorityQueue.poll();
            System.out.println("Removed: " + element);
        }
    }
}