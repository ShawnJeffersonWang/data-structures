package com.shawn.algorithm;


public class LeetCode701 {

    public TreeNode insertIntoBST(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }
        if (val < node.val) {
            // 父子
            node.left = insertIntoBST(node.left, val);
        } else if (val > node.val) {
            // 父子
            node.right = insertIntoBST(node.right, val);
        }
        return node;
    }
}


// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

