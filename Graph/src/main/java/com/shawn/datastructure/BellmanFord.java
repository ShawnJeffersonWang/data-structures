package com.shawn.datastructure;

import java.util.List;

/**
 * <h3>Bellman-Ford 算法，可以处理负边</h3>
 */
public class BellmanFord {

    public static void main(String[] args) {
        // 正常情况
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v2, 2), new Edge(v3, 1));
        v2.edges = List.of(new Edge(v3, -2));
        v3.edges = List.of(new Edge(v4, 1));
        v4.edges = List.of();

        List<Vertex> graph = List.of(v1, v2, v3, v4);
        bellmanFord(graph, v1);
        // 负边情况

        // 负环情况 (负环：1. 路径构成一个环 2. 环中所有路径权重加起来为负数)
    }

    private static void bellmanFord(List<Vertex> graph, Vertex source) {
        source.dist = 0;
        int size = graph.size();
        // 1. 进行 顶点个数 -1 轮处理 三轮之后还能找到最短距离，就说明有负环
        for (int i = 0; i < size - 1; i++) {
            // 2. 遍历所有的边
            for (Vertex s : graph) {
                for (Edge edge : s.edges) {
                    // 3. 处理每一条边
                    Vertex e = edge.linked;
                    if (s.dist != Integer.MAX_VALUE && s.dist + edge.weight < e.dist) {
                        e.dist = s.dist + edge.weight;
                        e.prev = s;
                    }
                }
            }
        }
        for (Vertex v : graph) {
            System.out.println(v + " " + (v.prev != null ? v.prev.name : "null"));
        }
    }
}
