package com.shawn.datastructure;

import java.util.LinkedList;

public class TreeTraversal {

    public static void main(String[] args) throws InterruptedException {

        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, new TreeNode(7)),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );
//        preOrder(root);
//        System.out.println();
//
//        inOrder(root);
//        System.out.println();
//
//        postOrder(root);
//        System.out.println();

                /*
        通过栈记住来时的路
         */
        LinkedList<TreeNode> stack1 = new LinkedList<>();

        // 代表当前节点
        TreeNode curr1 = root;
        while (curr1 != null || !stack1.isEmpty()) {
            if (curr1 != null) {
                // 想要中序遍历就注释这一行
//                colorPrintln("去: " + curr.val, 31);
                // 压入栈，为了记住回来的路
                stack1.push(curr1);
                curr1 = curr1.left;
            } else {
                TreeNode pop = stack1.pop();
                // 想要前序遍历就注释这一行
                colorPrintln("回: " + pop.val, 34);
                curr1 = pop.right;
            }
        }

        System.out.println("========后序遍历========");
        /*
        通过栈记住来时的路
         */
        LinkedList<TreeNode> stack2 = new LinkedList<>();

        // 代表当前节点
        TreeNode curr2 = root;
        // 最近一次弹栈的元素
        TreeNode pop2 = null;
        while (curr2 != null || !stack2.isEmpty()) {
            if (curr2 != null) {
                // 想要中序遍历就注释这一行
//                colorPrintln("去: " + curr.val, 31);
                // 压入栈，为了记住回来的路
                stack2.push(curr2);
                curr2 = curr2.left;
            } else {
                TreeNode peek = stack2.peek();
                // 右子树处理完成
                if (peek.right == null || peek.right == pop2) {
                    pop2 = stack2.pop();
                    colorPrintln("回: " + pop2.val, 34);
                } else {
                    curr2 = peek.right;
                }
            }
        }

        System.out.println("========统一写法========");
        LinkedList<TreeNode> stack = new LinkedList<>();

        // 代表当前节点
        TreeNode curr = root;
        // 最近一次弹栈的元素
        TreeNode pop = null;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                // 待处理左子树
                colorPrintln("前: " + curr.val, 31);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                // 没有右子树
                if (peek.right == null) {
                    colorPrintln("中: " + peek.val, 36);
                    pop = stack.pop();
                    colorPrintln("后: " + pop.val, 34);
                    // 右子树处理完成
                } else if (peek.right == pop) {
                    pop = stack.pop();
                    colorPrintln("后: " + pop.val, 34);
                } else {
                    colorPrintln("中: " + peek.val, 36);
                    // 待处理右子树
                    curr = peek.right;
                }
            }
        }
    }

    private static void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }

    /**
     * 前序遍历
     *
     * @Params: node - 节点
     */
    static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + "\t");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     *
     * @Params: node - 节点
     */
    static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.val + "\t");
        inOrder(node.right);
    }

    /**
     * 后序遍历
     *
     * @Params: node - 节点
     */
    static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + "\t");
    }
}
