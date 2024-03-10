package com.shawn.datastructure;

// 能不能让任意一个类型能够具备比较大小的能力，必须实现一个接口Comparable
// 声明泛型的时候能不能让泛型也具备比较大小的能力 -> 泛型上限的语法,extends
// 泛型T必须是Comparable的子类型
public class BSTree2<K extends Comparable<K>, V> {

    static class BSTNode<K, V> {
        K key;
        V value;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        public BSTNode(K key) {
            this.key = key;
        }

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    BSTNode<K, V> root;

    // key改为泛型传过来可能为null
    public V get(K key) {
        BSTNode<K, V> p = root;
        while (p != null) {
            // 泛型T是Comparable接口的子类型 既然是子类型，就可以用Comparable父类型中的方法
            /*
            -1  key < p.key
            0   key == p.key
            1   key > p.key
             */
            int result = key.compareTo(p.key);
            if (result < 0) {
                p = p.left;
            } else if (result > 0) {
                p = p.right;
            } else {
                return p.value;
            }
        }
        return null;
    }
}
