package com.shawn.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Binary Search Tree 二叉搜索树
 */
public class BSTree1 {

    // 根节点
    BSTNode root;

    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * <h3>查找关键字对应的值</h3>
     *
     * @param key 关键字
     * @return 关键字对应的值
     */
//    public Object get(int key) {
//        return doGet(root, key);
//    }


    // 尾递归：return调用函数自身，非常容易转为非递归的实现
//    private Object doGet(BSTNode node, int key) {
//        if (node == null) {
//            return null;
//        }
//        if (key < node.key) {
//            // 向左找
//            return doGet(node.left, key);
//        } else if (key > node.key) {
//            // 向右找
//            return doGet(node.right, key);
//        } else {
//            return node.value;
//        }
//    }
    public Object get(int key) {
        BSTNode node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    /**
     * 查找最小关键字对应的值
     *
     * @Returns: 关键字对应的值
     */
//    public Object min() {
//        return doMin(root);
//    }
//
//    public Object doMin(BSTNode node) {
//        if (node == null) {
//            return null;
//        }
//        // 最小的节点
//        if (node.left == null) {
//            return node.value;
//        }
//        return doMin(node.left);
//    }
    public Object min() {
        return min(root);
    }

    /**
     * 查找最大关键字对应值
     *
     * @Returns: 关键字对应的值
     */
    public Object max() {
        return max(root);
    }

    /**
     * 存储关键字和对应值
     * Params: key -
     * value - 值
     */
    public void put(int key, Object value) {
        // 1. key 有 更新
        // 2. key 没有 新增
        BSTNode node = root;
        BSTNode parent = null;

        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                // 1. key有 更新
                node.value = value;
                return;
            }
        }

        if (parent == null) {
            root = new BSTNode(key, value);
            return;
        }
        // 2. key 没有 新增
        if (key < parent.key) {
            parent.left = new BSTNode(key, value);
        } else {
            parent.right = new BSTNode(key, value);
        }
        // 不可能相等，相等会走更新的逻辑
    }

    /**
     * 查找关键字的后任值
     * Params: key - 后任值
     */
    // 二叉搜索树的中序遍历结果就是升序的结果
    public Object successor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromRight = null;
        while (p != null) {
            if (key > p.key) {
                p = p.right;
            } else if (key < p.key) {
                // 向左走就是自右而来
                ancestorFromRight = p;
                p = p.left;
            } else {
                break;
            }
        }

        if (p == null) {
            return null;
        }

        if (p.right != null) {
            return min(p.right);
        }

        return ancestorFromRight != null ?
                ancestorFromRight.value : null;
    }

    private Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }

    private Object min(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p.value;
    }

    /**
     * 查找关键字的前任值
     * Params: key - 关键字
     * Returns: 前任值
     */
    public Object predecessor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                ancestorFromLeft = p;
                p = p.right;
            } else {
                break;
            }
        }

        // 没找到节点
        // 找到节点
        /*
        情况1：节点没有左子树，此时前任就是左子树的最大值
        情况2：节点没有左子树，若离他最近的，自左而来的祖先就是前任
         */
        if (p == null) {
            return null;
        }

        if (p.left != null) {
            return max(p.left);
        }

        return ancestorFromLeft != null ?
                ancestorFromLeft.value : null;
    }

    /**
     * 根据关键字删除
     * 非递归形式-性能较高
     * Params: key - 关键字
     * Returns: 这删除关键字对应值
     */
//    public Object delete(int key) {
//        BSTNode p = root;
//        BSTNode parent = null;
//
//        while (p != null) {
//            if (key < p.key) {
//                parent = p;
//                p = p.left;
//            } else if (key > p.key) {
//                parent = p;
//                p = p.right;
//            } else {
//                break;
//            }
//        }
//
//        if (p == null) {
//            return null;
//        }
//
//        // 删除操作 情况1 自动包含情况3
//        if (p.left == null) {
//            shift(parent, p, p.right);
//        } else if (p.right == null) {
//            // 情况2
//            shift(parent, p, p.left);
//        } else {
//            // 情况4
//            // 4.1 被删除节点找后继
//            BSTNode s = p.right;
//            // 后继父亲
//            BSTNode sParent = p;
//            while (s.left != null) {
//                sParent = s;
//                s = s.left;
//            }
//
//            // 后继节点即为s
//            // 不相邻
//            if (sParent != p) {
//                // 4.2 删除节点和后继节点不相邻，处理后继的后事
//                // 不可能有左孩子
//                shift(sParent, s, s.right);
//                s.right = p.right;
//            }
//            // 4.3 后继取代被删除节点
//            shift(parent, p, s);
//            s.left = p.left;
//        }
//        return p.value;
//    }
    public Object delete(int key) {
        // 保存被删除节点的值
        ArrayList<Object> result = new ArrayList<>();
        root = doDelete(root, key, result);
        return result.isEmpty() ? null : result.get(0);
    }

    /*
        node 起点
        返回值 删剩下的孩子(找到) 或null(没找到)
     */
    private BSTNode doDelete(BSTNode node, int key, ArrayList<Object> result) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.left = doDelete(node.left, key, result);
            return node;
        } else if (key > node.key) {
            node.right = doDelete(node.right, key, result);
            return node;
        }
        result.add(node.value);
        // 情况1 - 只有右孩子
        if (node.left == null) {
            return node.right;
        }
        // 情况2 - 只有左孩子
        if (node.right == null) {
            return node.left;
        }
        // 情况3 - 有两个孩子
        BSTNode s = node.right;
        while (s.left != null) {
            s = s.left;
        }
        s.right = doDelete(node.right, s.key, result);
        s.left = node.left;
        return s;
    }

    /**
     * 托孤方法
     * Params: parent - 被删除节点的父亲
     * deleted - 被删除节点
     * child - 被顶上去的节点
     */
    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    // 找 < key 的所有value
    public List<Object> less(int key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                // 处理值
                if (pop.key < key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

    // 找 > key 的所有value
    // 使用反向中序遍历 Reverse in-order, RNL
    public List<Object> greater(int key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.right;
            } else {
                BSTNode pop = stack.pop();
//                System.out.print(pop.value + "\t");
                if (pop.key > key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.left;
            }
        }
        return result;
    }

    // 找 >= key1 且 <= key2 的所有值
    public List<Object> between(int key1, int key2) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                // 处理值
                if (pop.key >= key1 && pop.key <= key2) {
                    result.add(pop.value);
                } else if (pop.key > key2) {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }
}
