package com.alibaba.datastructure;

import java.util.Iterator;

// 基于链表的实现
public class LinkedListStack<E> implements Stack<E>, Iterable<E> {

    private int capacity;
    private int size;
    // 只需要操作一端，不需要tail尾指针
    private Node<E> head = new Node<>(null, null);

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
    }

    // 单向链表配哨兵节点实现栈
    static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    /*
        head -> 1 -> null
     */
    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        head.next = new Node<>(value, head.next);
        size++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        Node<E> first = head.next;
        head.next = first.next;
        size--;
        return first.value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
//        return head.next==null;
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                // 以null作为链表结尾
                return p != null;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }
}
