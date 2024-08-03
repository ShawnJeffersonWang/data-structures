package com.shawn.algorithm;

import java.util.Scanner;

public class t3508 {
    int n, m;
    final int N = 10;
    int[][] a = new int[N + 1][N + 1];
    int[][] c = new int[N + 2][N + 2];
    boolean flag = false;

    //检查x, y处的字符是否不满足要求
    boolean check(int x, int y) {
        if (a[x][y] == -1) return false; //该方格为下滑线，不需要检查
        int count = 0;
        //检查周围黑色方格的数量是否满足要求
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (c[i][j] == 1) count++;
            }
        }
        if (count == a[x][y]) return false;
        return true;
    }

    void show() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) System.out.print(c[i][j]);
            System.out.println();
        }
    }

    void dfs(int x, int y) {
        //已经找到答案，直接返回
        if (flag) return;
        //检查周围像素已经确定的字符
        if (x > 1 && y > 2) {
            if (check(x - 1, y - 2)) return;
        }
        //换行后，检查上上行未检查的两个字符
        if (x >= 3 && y == 1) {
            if (check(x - 2, m - 1) || check(x - 2, m)) return;
        }
        //所有像素都已填好
        if (x == n + 1 && y == 1) {
            //检查最后一行的像素
            for (int j = 1; j <= m; j++) {
                if (check(n, j)) return;
            }
            flag = true;
            show();
            return;
        }
        //下一个搜索的位置
        int x1, y1;
        if (y < m) {//尚未到行尾
            x1 = x;
            y1 = y + 1;
        } else {
            x1 = x + 1;
            y1 = 1;
        }
        //填充黑色
        c[x][y] = 1;
        dfs(x1, y1);
        c[x][y] = 0;
        //填充白色
        dfs(x1, y1);
    }

    void solve() {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            char[] s = scan.next().toCharArray();
            for (int j = 1; j <= m; j++) {
                char c = s[j - 1];
                if (c != '_') {
                    int x = c - '0';
                    a[i][j] = x;
                } else a[i][j] = -1;
            }
        }
        dfs(1, 1);
    }

    public static void main(String[] args) {
        new t3508().solve();
    }
}