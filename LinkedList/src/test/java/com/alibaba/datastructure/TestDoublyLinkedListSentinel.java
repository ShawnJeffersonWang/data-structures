package com.alibaba.datastructure;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDoublyLinkedListSentinel {

    @Test
    @DisplayName("测试 addFirst")
    public void test1() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        for (Integer value : list) {
            System.out.println(value);
        }
    }

    @Test
    @DisplayName("测试 addLast")
    public void test2() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        Assertions.assertIterableEquals(List.of(1, 2, 3, 4), list);
    }

    @Test
    @DisplayName("测试 get")
    public void test3() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

//        int i = list.get(2);
//        System.out.println(i);
        assertEquals(3, list.get(2));
        // 验证异常，是不是和期望中的异常一致
        assertThrows(IllegalArgumentException.class, () -> list.get(10));
    }

    @Test
    @DisplayName("测试 insert")
    public void test4() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();

        list.addLast(1); // 0
        list.addLast(2); // 1
        list.addLast(3); // 2
        list.addLast(4); // 3
        list.insert(0, 5);

        assertIterableEquals(List.of(5, 1, 2, 3, 4), list);
    }

    @Test
    @DisplayName("测试 removeFirst")
    public void test5() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.removeFirst();
        assertIterableEquals(List.of(2, 3, 4), list);
    }

    @Test
    @DisplayName("测试 remove")
    public void test6() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.remove(2);
        assertIterableEquals(List.of(1, 2, 4), list);
    }
}
