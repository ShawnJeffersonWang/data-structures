package com.shawn.datastructure;

import java.util.Arrays;

/**
 * <h3>不相交集合（并查集合）</h3>
 */
public class DisjointSet {
    int[] s;

    public DisjointSet(int size) {
        this.s = new int[size];
        Arrays.fill(s, -1);
    }

    // 一个 union 方法和 find 方法，基于这两个方法，就有了并查集这个名字
    // 索引和值相等的才是老大，不相等的就是小弟
    // find 是找到老大
    public int find(int x) {
        if (s[x] < 0) {
            return x;
        } else {
            return find(s[x]);
        }
    }

    // union 不对应连线操作，应该连两边的老大
    // union 是让两个集合“相交”，即选出新老大，x, y 是原老大索引
    public void union(int x, int y) {
        s[y] = x;
    }

    @Override
    public String toString() {
        return Arrays.toString(s);
    }

    public static void main(String[] args) {
        DisjointSet set = new DisjointSet(7);
        System.out.println(set);

        int x = set.find(0);
        int y = set.find(3);
        System.out.println("老大分别是：" + x + " " + y);
        if (x != y) {
            set.union(x, y);
            System.out.println(set);
        }
        // 索引对应顶点
        // 元素用来表示与之有关系的顶点

        /*
            索引 0 1 2 3 4 5 6
            元素 [0, 1, 2, 3, 4, 5, 6] 表示一开始顶点直接没有联系（只与自己有联系）
         */
    }
}
