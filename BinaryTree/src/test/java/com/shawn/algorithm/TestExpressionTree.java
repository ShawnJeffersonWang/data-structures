package com.shawn.algorithm;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestExpressionTree {

    ExpressionTree e08 = new ExpressionTree();

    /*
    传递一个空数组的好处是，无论集合中有多少元素，它都不会浪费额外的空间。
    该方法将根据集合的大小自动创建一个具有正确大小的新数组，并将元素复制到新数组中。
    这种用法相对简洁并且是一种常见的实践方式。
     */
    @Test
    void test1() {
        String[] tokens = {"2", "1", "-", "3", "*"};
        ExpressionTree.TreeNode root = e08.constructExpressionTree(tokens);
        ArrayList<String> result = new ArrayList<>();
        post(root, result);
        assertArrayEquals(tokens, result.toArray(new String[0]));
    }

    @Test
    void test2() {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*"};
        ExpressionTree.TreeNode root = e08.constructExpressionTree(tokens);
        ArrayList<String> result = new ArrayList<>();
        post(root, result);
        assertArrayEquals(tokens, result.toArray(new String[0]));
    }

    private void post(ExpressionTree.TreeNode node, ArrayList<String> result) {
        if (node == null) {
            return;
        }
        post(node.left,result);
        post(node.right,result);
        result.add(node.val);
    }
}
