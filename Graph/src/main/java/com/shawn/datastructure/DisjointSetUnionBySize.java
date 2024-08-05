package com.shawn.datastructure;

import java.util.Arrays;

/**
 * <h3>不相交集合（并查集合）优化2：union by size</h3>
 */
public class DisjointSetUnionBySize {
    int[] s;
    int[] size;

    public DisjointSetUnionBySize(int size) {
        s = new int[size];
        this.size = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;
            this.size[i] = 1;
        }
    }

    // find 是找到老大 - 优化：路径压缩
    public int find(int x) { // x = 2
        if (x == s[x]) {
            return x;
        }
        return s[x] = find(s[x]); // 0    s[2]=0
    }

    // union 是让两个集合“相交”，即选出新老大，x、y 是原老大索引
    public void union(int x, int y) {
        /*if (size[x] < size[y]) {
            // y 老大  x 小弟
            s[x] = y;
            size[y] = size[x] + size[y]; // 更新老大元素个数
        } else {
            // x 新老大 y 新小弟
            s[y] = x;
            size[x] = size[x] + size[y]; // 更新老大元素个数
        }*/

        if (size[x] < size[y]) {
            int t = x;
            x = y;
            y = t;
        }
        s[y] = x;
        size[x] = size[x] + size[y]; // 更新老大元素个数
    }

    @Override
    public String toString() {
        return "内容：" + Arrays.toString(s) + "\n大小：" + Arrays.toString(size);
    }

    public static void main(String[] args) {
        DisjointSetUnionBySize set = new DisjointSetUnionBySize(5);

        set.union(1, 2);
        set.union(3, 4);
        set.union(1, 3);
        System.out.println(set);

        set.union(1, 0);
        System.out.println(set);
    }


}
