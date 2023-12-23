package com.alibaba.datastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDoublyCircularLinkedList {

    private DoublyCircularLinkedList getList() {
        DoublyCircularLinkedList list = new DoublyCircularLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        return list;
    }

    @Test
    @DisplayName("测试 addFirst")
    public void test1() {
        DoublyCircularLinkedList list = new DoublyCircularLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        assertIterableEquals(List.of(5, 4, 3, 2, 1), list);
        System.out.println(list);
    }

    @Test
    @DisplayName("测试 addLast")
    public void test2() {
        DoublyCircularLinkedList list = new DoublyCircularLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        Assertions.assertIterableEquals(List.of(1, 2, 3, 4), list);
    }

    @Test
    @DisplayName("测试 removeLast")
    public void test3() {
        DoublyCircularLinkedList list=getList();
        list.removeLast();
        assertIterableEquals(List.of(1,2,3,4),list);
        list.removeLast();
        assertIterableEquals(List.of(1,2,3),list);
        list.removeLast();
        assertIterableEquals(List.of(1,2),list);
        list.removeLast();
        assertIterableEquals(List.of(1),list);
        list.removeLast();
        assertIterableEquals(List.of(),list);
        assertThrows(IllegalArgumentException.class,list::removeLast);

    }

    @Test
    @DisplayName("测试 removeFirst")
    public void test4() {
        DoublyCircularLinkedList list = new DoublyCircularLinkedList();

        list.addLast(1); // 0
        list.addLast(2); // 1
        list.addLast(3); // 2
        list.addLast(4); // 3
        list.removeFirst();

        assertIterableEquals(List.of(2, 3, 4), list);
    }

    @Test
    @DisplayName("测试 removeFirst")
    public void test5() {
        DoublyCircularLinkedList list = getList();
        list.removeFirst();
        assertIterableEquals(List.of(2, 3, 4, 5), list);
    }

    @Test
    @DisplayName("测试 removeByValue")
    public void test6() {
        DoublyCircularLinkedList list = getList();
        list.removeByValue(1);
        assertIterableEquals(List.of(2, 3, 4, 5), list);
        list.removeByValue(2);
        assertIterableEquals(List.of(3, 4, 5), list);
    }
}
