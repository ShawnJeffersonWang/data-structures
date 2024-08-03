package com.shawn.algorithm;


import java.util.LinkedList;

/**
 * 根据后缀表达式构造表达式树
 */
public class ExpressionTree {

    /*
    中缀表达式           (2-1)*3
    后缀(逆波兰)表达式    21-3*

    1. 遇到数字入栈
    2. 遇到运算符出栈，建立节点关系，再入栈

              表达式树
                 *
                / \
               -   3
              / \
             2   1
      */

    static class TreeNode {
        public String val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, String val, TreeNode right) {
            this.left = left;
            this.val = val;
            this.right = right;
        }

        @Override
        public String toString() {
            return this.val;
        }
    }


    public TreeNode constructExpressionTree(String[] tokens) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        for (String t : tokens) {
            switch (t) {
                case "+", "-", "*", "/" -> {
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    TreeNode parent = new TreeNode(t);
                    parent.left = left;
                    parent.right = right;
                    stack.push(parent);
                }
                default -> {
                    stack.push(new TreeNode(t));
                }
            }
        }
        return stack.peek();
    }
}
