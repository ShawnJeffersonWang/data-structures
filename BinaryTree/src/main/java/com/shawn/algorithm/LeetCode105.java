package com.shawn.algorithm;

import com.shawn.datastructure.TreeNode;

import java.util.Arrays;

public class LeetCode105 {

    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0) {
            return null;
        }

        // 创建根节点
        int rootValue = preOrder[0];
        TreeNode root = new TreeNode(rootValue);
        // 区分左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootValue) {
                // 0~i-1 左子树
                // i+1~inOrder.length-1 右子树
                int[] inLeft = Arrays.copyOfRange(inOrder, 0, i);
                int[] inRight = Arrays.copyOfRange(inOrder, i + 1, inOrder.length);

                int[] preLeft = Arrays.copyOfRange(preOrder, 1, i + 1);
                int[] preRight = Arrays.copyOfRange(preLeft, i + 1, inOrder.length);

                root.left = buildTree(preLeft, inOrder);
                root.right = buildTree(preRight, inRight);
                break;
            }
        }
        return root;
    }
}
