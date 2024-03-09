package com.shawn.algorithm;

import com.shawn.datastructure.TreeNode;

import java.util.Arrays;


public class LeetCode106 {

    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        if (inOrder.length == 0) {
            return null;
        }

        int rootValue = postOrder[postOrder.length - 1];
        TreeNode root = new TreeNode(rootValue);
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootValue) {
                int[] inLeft = Arrays.copyOfRange(inOrder, 0, i);
                int[] inRight = Arrays.copyOfRange(inOrder, i + 1, inOrder.length);

                int[] postLeft = Arrays.copyOfRange(postOrder, 0, i);
                int[] postRight = Arrays.copyOfRange(postOrder, i, postOrder.length - 1);

                root.left = buildTree(inLeft, postLeft);
                root.right = buildTree(inRight, postRight);
                break;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new LeetCode106().buildTree(new int[]{4, 2, 1, 6, 3, 7}, new int[]{4, 2, 6, 7, 3, 1});
        fn(root);
    }

    static void fn(TreeNode root) {
        if (root == null) {
            return;
        }
        fn(root.left);
        fn(root.right);
        System.out.print(root.val + "\t");
    }
}
