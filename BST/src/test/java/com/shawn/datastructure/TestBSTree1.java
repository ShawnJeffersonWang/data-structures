package com.shawn.datastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBSTree1 {

    public BSTree1 createTree() {

        /*
                             4
                          /     \
                         2       6
                        / \     / \
                       1   3   5   7
         */
        BSTree1.BSTNode n1 = new BSTree1.BSTNode(1, "zhang");
        BSTree1.BSTNode n3 = new BSTree1.BSTNode(3, "song");
        BSTree1.BSTNode n2 = new BSTree1.BSTNode(2, "zhou", n1, n3);

        BSTree1.BSTNode n5 = new BSTree1.BSTNode(5, "shawn");
        BSTree1.BSTNode n7 = new BSTree1.BSTNode(7, "ying");
        BSTree1.BSTNode n6 = new BSTree1.BSTNode(6, "zhao", n5, n7);
        BSTree1.BSTNode root = new BSTree1.BSTNode(4, "xiao", n2, n6);

        BSTree1 tree = new BSTree1();
        tree.root = root;
        return tree;
    }

    @Test
    void get() {
        BSTree1 tree = createTree();
        assertEquals("zhang", tree.get(1));
        assertEquals("zhou", tree.get(2));
        assertEquals("song", tree.get(3));
        assertEquals("xiao", tree.get(4));
        assertEquals("shawn", tree.get(5));
        assertEquals("zhao", tree.get(6));
        assertEquals("ying", tree.get(7));
        assertNull(tree.get(8));
    }

    @Test
    public void minMax() {
        BSTree1 tree = createTree();
        assertEquals("zhang", tree.min());
        assertEquals("ying", tree.max());
    }

    @Test
    public void put() {
        BSTree1 tree = createTree();
        tree.put(4, new Object());
        tree.put(2, new Object());
        tree.put(6, new Object());
        tree.put(1, new Object());
        tree.put(3, new Object());
        tree.put(7, new Object());
        tree.put(5, new Object());
        assertTrue(isSameTree(createTree().root, tree.root));
        tree.put(1, "zhang");
        assertEquals("zhang", tree.get(1));
    }

    static boolean isSameTree(BSTree1.BSTNode tree1, BSTree1.BSTNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        if (tree1.key != tree2.key) {
            return false;
        }
        return isSameTree(tree1.left, tree2.left) && isSameTree(tree2.right, tree2.right);
    }

    @Test
    public void delete2() {

        /*
                         4
                      /     \
                     2       7
                    /\      /
                   1  3    6
                         /
                        5
         */
        BSTree1.BSTNode n1 = new BSTree1.BSTNode(1, 1);
        BSTree1.BSTNode n3 = new BSTree1.BSTNode(3, 3);
        BSTree1.BSTNode n2 = new BSTree1.BSTNode(2, 2, n1, n3);

        BSTree1.BSTNode n5 = new BSTree1.BSTNode(5, 5);
        BSTree1.BSTNode n6 = new BSTree1.BSTNode(6, 6, n5, null);
        BSTree1.BSTNode n7 = new BSTree1.BSTNode(7, 7, n6, null);
        BSTree1.BSTNode root1 = new BSTree1.BSTNode(4, 4, n2, n7);

        BSTree1 tree1 = new BSTree1();
        tree1.root = root1;

        assertEquals(7, tree1.delete(7));

        /*
                         4
                      /     \
                     2       6
                    /\      /
                   1  3    5
         */
        BSTree1.BSTNode x1 = new BSTree1.BSTNode(1, 1);
        BSTree1.BSTNode x3 = new BSTree1.BSTNode(3, 3);
        BSTree1.BSTNode x2 = new BSTree1.BSTNode(2, 2, x1, x3);
        BSTree1.BSTNode x5 = new BSTree1.BSTNode(2, 2);
        BSTree1.BSTNode x6 = new BSTree1.BSTNode(2, 2, x5, null);
        BSTree1.BSTNode root2 = new BSTree1.BSTNode(4, 4, x2, x6);
        BSTree1 tree2 = new BSTree1();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }
}
