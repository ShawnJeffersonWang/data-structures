package com.shawn.algorithm;

import java.util.Arrays;

public class BTree {

    static class Node {
        // 关键字
        int[] keys;
        // 孩子
        Node[] children;
        // 有效关键字数目
        int keyNumber;
        // 是否是叶子节点
        boolean leaf = true;
        // 最小度数
        int t;

        public Node(int t) {
            // t>=2
            this.t = t;
            this.children = new Node[2 * t];
            this.keys = new int[2 * t - 1];
        }

        public Node(int[] keys) {
            this.keys = keys;
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        // 多路查找
        Node get(int key) {
            int i = 0;
            while (i < keyNumber) {
                if (keys[i] == key) {
                    return this;
                }
                if (keys[i] > key) {
                    break;
                }
                i++;
            }
            // 执行到此时 keys[i] > key 或 i == keyNumber
            if (leaf) {
                return null;
            }
            // 非叶子情况
            return children[i].get(key);
        }

        // 向 keys 指定索引处插入 key
        void insertKey(int key, int index) {
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }

        // 向 children 指定索引处插入 child
        void insertChild(Node child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }

        // 移除指定 index 处的 key
        int removeKey(int index) {
            int t = keys[index];
            System.arraycopy(keys, index + 1, keys, index, --keyNumber - index);
            return t;
        }

        // 移除最左边的 key
        int removeLeftmostKey() {
            return removeKey(0);
        }

        // 移除最右边的 key
        int removeRightmostKey() {
            return removeKey(keyNumber - 1);
        }

        // 移除指定 index 处的 child
        Node removeChild(int index) {
            Node t = children[index];
            System.arraycopy(children, index + 1, children, index, keyNumber - index);
            children[keyNumber] = null;
            return t;
        }

        // 移除最左边的 child
        Node removeLeftmostChild() {
            return removeChild(0);
        }

        // 移除最右边的 child
        Node removeRightmostChild() {
            return removeChild(keyNumber);
        }

        // index 孩子处左边的兄弟
        Node childLeftSibling(int index) {
            return index > 0 ? children[index - 1] : null;
        }

        // index 孩子处右边的兄弟
        Node childRightSibling(int index) {
            return index == keyNumber ? null : children[index + 1];
        }

        // 复制当前节点的所有 key 和 child 到 target
        void moveToTarget(Node target) {
            int start = target.keyNumber;
            if (!leaf) {
                for (int i = 0; i <= keyNumber; i++) {
                    target.children[start + i] = children[i];
                }
            }
            for (int i = 0; i < keyNumber; i++) {
                target.keys[target.keyNumber++] = keys[i];
            }
        }
    }

    Node root;
    // 树中节点最小度数
    int t;
    // 最小key数目
    final int MIN_KEY_NUMBER;
    // 最大key数目
    final int MAX_KEY_NUMBER;

    public BTree() {
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1;
    }

    // 1. 是否存在
    public boolean contains(int key) {
        return root.get(key) != null;
    }

    // 2. 新增
    public void put(int key) {
        doPut(root, key, null, 0);
    }

    private void doPut(Node node, int key, Node parent, int index) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] == key) {
                // 更新
                return;
            }
            if (node.keys[i] > key) {
                // 找到了插入位置，即为此时的 i
                break;
            }
            i++;
        }
        if (node.leaf) {
            node.insertKey(key, i);
            // 上限
        } else {
            doPut(node.children[i], key, node, i);
            // 上限
        }
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
    }

    /**
     * 分裂方法
     *
     * @param left   要分裂的节点
     * @param parent 分裂节点的父节点
     * @param index  分裂节点是第几个孩子
     */
    void split(Node left, Node parent, int index) {
        // 分裂的是根节点
        if (parent == null) {
            Node newRoot = new Node(t);
            newRoot.leaf = false;
            newRoot.insertChild(left, 0);
            this.root = newRoot;
            parent = newRoot;
        }
        // 1. 创建 right 节点，把 left 中 t 之后的 key 和 child 移动过去
        Node right = new Node(t);
        right.leaf = left.leaf;
        System.arraycopy(left.keys, t,
                right.keys, 0, t - 1);
        if (!left.leaf) {
            System.arraycopy(left.children, t, right.children, 0, t);
        }
        right.keyNumber = t - 1;
        left.keyNumber = t - 1;
        // 2. 中间的 key (t - 1) 插入到父节点
        int mid = left.keys[t - 1];
        parent.insertKey(mid, index);
        // 3. right 节点作为父节点的孩子
        parent.insertChild(right, index + 1);
    }

    // 3. 删除
    public void remove(int key) {
        doRemove(null, root, 0, key);
    }

    private void doRemove(Node parent, Node node, int index, int key) {

    }

    public void travel() {
        doTravel(root);
    }

    public void doTravel(Node node) {
        if (node == null) {
            return;
        }
        int i = 0;
        for (; i < node.keyNumber; i++) {
            doTravel(node.children[i]);
            System.out.println(node.keys[i]);
        }
        doTravel(node.children[i]);
    }
}
