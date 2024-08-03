package com.shawn.algorithm;

import com.shawn.datastructure.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLeetCode111 {

    @Test
    public void test1() {
        TreeNode root = new TreeNode(new TreeNode(2), 1, new TreeNode(3));
        assertEquals(2, new LeetCode111().minDepth(root));
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(new TreeNode(2), 1, new TreeNode(3));
        assertEquals(2, new LeetCode111().minDepth(root));
    }

    @Test
    public void test3() {
        TreeNode root = new TreeNode(1);
        assertEquals(1, new LeetCode111().minDepth(root));
    }

    // 有一颗子树为null时，不能计算在内
    @Test
    public void test4() {
        TreeNode root = new TreeNode(new TreeNode(2), 1, null);
        assertEquals(2, new LeetCode111().minDepth(root));
    }
}
