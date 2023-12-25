package com.alibaba.algorithm;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * LeetCode很多链表题目用到的节点类
 */
public class ListNode implements Iterable<Integer> {

    public int val;
    public ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder(64);
//        sb.append("[");
//        ListNode p = this;
//        while (p != null) {
//            sb.append(p.val);
//            if (p.next != null) {
//                sb.append(",");
//            }
//            p = p.next;
//        }
//        sb.append("]");
//        return sb.toString();
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("[");
        ListNode p = this;
        while (p != null) {
            sb.append(p.val);
            if (p.next != null) {
                sb.append(", ");
            }
            p = p.next;
        }
        sb.append("]");
        return sb.toString();
//        return String.valueOf(this.val);
    }

    public static ListNode of(int... elements) {
        if (elements.length == 0) {
            return null;
        }
        ListNode p = null;
        for (int i = elements.length - 1; i >= 0; i--) {
            p = new ListNode(elements[i], p);
        }
        return p;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ListNodeIterator(this);
    }

    private static class ListNodeIterator implements Iterator<Integer> {

        private ListNode current;

        public ListNodeIterator(ListNode head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Integer next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            int value = current.val;
            current = current.next;
            return value;
        }
    }
}
