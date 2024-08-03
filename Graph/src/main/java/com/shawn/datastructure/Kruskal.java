package com.shawn.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <h3>最小生成树 - Kruskal算法</h3>
 */
public class Kruskal {

    static class Edge implements Comparable<Edge> {
        List<Vertex> vertices;
        int start;
        int end;
        int weight;

        public Edge(List<Vertex> vertices, int start, int end, int weight) {
            this.vertices = vertices;
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return vertices.get(start).name + "<->" + vertices.get(end).name + "(" + weight + ")";
        }
    }

    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");
        List<Vertex> vertices = List.of(v1, v2, v3, v4, v5, v6, v7);

        PriorityQueue<Edge> queue = new PriorityQueue<>(List.of(
                new Edge(vertices, 0, 1, 2),
                new Edge(vertices, 0, 2, 4),
                new Edge(vertices, 0, 3, 1),
                new Edge(vertices, 1, 3, 3),
                new Edge(vertices, 1, 4, 10),
                new Edge(vertices, 2, 3, 2),
                new Edge(vertices, 2, 5, 5),
                new Edge(vertices, 3, 4, 7),
                new Edge(vertices, 3, 5, 8),
                new Edge(vertices, 3, 6, 4),
                new Edge(vertices, 4, 6, 6),
                new Edge(vertices, 5, 6, 1)
        ));
        kruskal(vertices.size(), queue);
    }

    static void kruskal(int size, PriorityQueue<Edge> queue) {
        List<Edge> list = new ArrayList<>();
        DisjointSet set = new DisjointSet(size);
        while (list.size() < size - 1) {
            // 接下来的一个难点是怎么判断这条边的两个顶点是否连通呢，有一个方便的数据结构就是并查集合
            Edge poll = queue.poll();
            int i = set.find(poll.start);
            int j = set.find(poll.end);
            // 尚未相交
            if (i != j) {
                list.add(poll);
                // 将两个顶点相交
                set.union(i, j);
            }
        }
        for (Edge edge : list) {
            // 边已经重写了toString，直接打印就可以了
            System.out.println(edge);
        }
    }
}
