package com.shawn.algorithm;

import com.shawn.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode94 {

    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pop = null;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                // 没有右子树
                if (peek.right == null) {
                    result.add(peek.val);
                    pop = stack.pop();
                    // 右子树处理完成
                } else if (peek.right == pop) {
                    pop = stack.pop();
                    // 待处理右子树
                } else {
                    result.add(peek.val);
                    curr = peek.right;
                }
            }
        }
        return result;
    }
}
