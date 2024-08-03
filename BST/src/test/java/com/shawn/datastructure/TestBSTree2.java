package com.shawn.datastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestBSTree2 {


    public BSTree2<String, String> createTree() {

        /*
                             4
                          /     \
                         2       6
                        / \     / \
                       1   3   5   7
         */
        BSTree2.BSTNode<String, String> n1 = new BSTree2.BSTNode<>("a", "zhang");
        BSTree2.BSTNode<String, String> n3 = new BSTree2.BSTNode<>("c", "song");
        BSTree2.BSTNode<String, String> n2 = new BSTree2.BSTNode<>("b", "zhou", n1, n3);

        BSTree2.BSTNode<String, String> n5 = new BSTree2.BSTNode<>("e", "shawn");
        BSTree2.BSTNode<String, String> n7 = new BSTree2.BSTNode<>("g", "ying");
        BSTree2.BSTNode<String, String> n6 = new BSTree2.BSTNode<>("f", "zhao", n5, n7);
        BSTree2.BSTNode<String, String> root = new BSTree2.BSTNode<>("d", "xiao", n2, n6);

        BSTree2<String, String> tree = new BSTree2<>();
        tree.root = root;
        return tree;
    }

    @Test
    void get() {
        BSTree2<String, String> tree = createTree();
        assertEquals("zhang", tree.get("a"));
        assertEquals("zhou", tree.get("b"));
        assertEquals("song", tree.get("c"));
        assertEquals("xiao", tree.get("d"));
        assertEquals("shawn", tree.get("e"));
        assertEquals("zhao", tree.get("f"));
        assertEquals("ying", tree.get("g"));
        assertNull(tree.get("h"));
    }
}
