package com.shawn.algorithm;

import com.shawn.datastructure.TreeNode;
import org.jetbrains.annotations.NotNull;

/**
 * 对称二叉树
 */
public class LeetCode101 {

    public boolean isSymmetric(@NotNull TreeNode root) {
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (right == null || left == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }
}
