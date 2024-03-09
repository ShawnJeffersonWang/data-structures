package com.shawn.algorithm;

import com.shawn.datastructure.TreeNode;

/**
 * 二叉树最小深度
 */
public class LeetCode111 {

    public int minDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int d1 = minDepth(node.left);
        int d2 = minDepth(node.right);
        // 当右子树为null
        if (d2 == 0) {
            return d1 + 1;
        }
        // 当左子树为null
        if (d1 == 0) {
            return d2 + 1;
        }
        return Integer.min(d1, d2) + 1;
    }
}
