package com.shawn.algorithm;

import com.shawn.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode145 {

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        // 最近一次弹栈的元素
        TreeNode pop = null;

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null) {
                    pop = stack.pop();
                    result.add(pop.val);
                } else if (peek.right == pop) {
                    pop = stack.pop();
                    result.add(pop.val);
                } else {
                    curr = peek.right;
                }
            }
        }
        return result;
    }
}
