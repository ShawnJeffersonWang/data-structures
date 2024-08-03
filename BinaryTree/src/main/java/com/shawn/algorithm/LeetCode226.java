package com.shawn.algorithm;

import com.shawn.datastructure.TreeNode;

/**
 * 翻转二叉树
 */
public class LeetCode226 {

    public TreeNode invertTree(TreeNode root) {
        fn(root);
        return root;
    }

    private static void fn(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode t = node.left;
        node.left = node.right;
        node.right = t;
        fn(node.left);
        fn(node.right);
    }
}
