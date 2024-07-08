package com.shawn.algorithm;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class LeetCode23 {

    public static void main(String[] args) {
        ListNode[] lists = {
                ListNode.of(1, 4, 5),
                ListNode.of(1, 3, 4),
                ListNode.of(2, 6),
                null,
        };
        ListNode m = new LeetCode23().mergeKLists(lists);
        System.out.println(m);
    }

    @Test
    public void mergeKLists() {
        ListNode[] lists = {
                ListNode.of(1, 4, 5),
                ListNode.of(1, 3, 4),
                ListNode.of(2, 6),
                null,
        };
        ListNode m = new LeetCode23().mergeKLists(lists);
        assertIterableEquals(List.of(1, 1, 2, 3, 4, 4, 5, 6), m);
    }

    private ListNode mergeKLists(ListNode[] lists) {
        MinHeap heap = new MinHeap(lists.length);
        // 将链表的头结点加入小顶堆
        for (ListNode h : lists) {
            if (h != null) {
                heap.offer(h);
            }
        }
        // 不断从堆顶移除最小元素，加入新链表
        ListNode s = new ListNode(-1, null);
        ListNode t = s;
        while (!heap.isEmpty()) {
            ListNode min = heap.poll();
            t.next = min;
            t = min;
            if (min.next != null) {
                heap.offer(min.next);
            }
        }
        return s.next;
    }
}
