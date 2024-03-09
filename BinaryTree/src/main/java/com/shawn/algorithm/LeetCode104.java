package com.shawn.algorithm;

import com.shawn.datastructure.TreeNode;


/*
思路：
1. 得到左子树深度，得到右子树深度，二者最大者加一，就是本节点深度
2. 因此需要先得到左右子树深度，很显然是后序遍历经典应用
3. 关于深度的定义：从根出发，离根最远的节点总边数，力扣里的深度定义要多一
 */
public class LeetCode104 {

    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
//        if (node.left == null && node.right == null) {
//            return 1;
//        }
        int d1 = maxDepth(node.left);
        int d2 = maxDepth(node.right);
        return Integer.max(d1, d2) + 1;
    }
}
