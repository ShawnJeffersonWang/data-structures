package com.shawn.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TopologicalSortDFS {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("网页基础");
        Vertex v2 = new Vertex("Java基础");
        Vertex v3 = new Vertex("JavaWeb");
        Vertex v4 = new Vertex("Spring框架");
        Vertex v5 = new Vertex("微服务框架");
        Vertex v6 = new Vertex("数据库");
        Vertex v7 = new Vertex("实战项目");

        v1.edges = List.of(new Edge(v3)); // +1
        v2.edges = List.of(new Edge(v3)); // +1
        v3.edges = List.of(new Edge(v4));
        v6.edges = List.of(new Edge(v4));
        v4.edges = List.of(new Edge(v5));
        v5.edges = List.of(new Edge(v7));
        // 如果有环的话会导致微服务框架入度不会减成0, 导致不会加入到队列中去
        v7.edges = List.of(new Edge(v5));
//        v7.edges = List.of();

        List<Vertex> graph = new ArrayList<>(List.of(v1, v2, v3, v4, v5, v6, v7));
        LinkedList<String> stack = new LinkedList<>();
        // 统计每个顶点的入度
        for (Vertex v : graph) {
            dfs(v, stack);
        }
//        for (Vertex vertex : graph) {
//            System.out.println(vertex.name + " " + vertex.inDegree);
//        }
        // 2. 将入度为0的顶点加入队列
        LinkedList<Vertex> queue = new LinkedList<>();
        for (Vertex vertex : graph) {
            if (vertex.inDegree == 0) {
                queue.offer(vertex);
            }
        }
        System.out.println(stack);
    }

    private static void dfs(Vertex v, LinkedList<String> stack) {
        if (v.status == 2) {
            return;
        }
        if (v.status == 1) {
            throw new RuntimeException("发现了环");
        }
        v.status = 1;
        for (Edge edge : v.edges) {
            dfs(edge.linked, stack);
        }
        v.status = 2;
        // 向回走的时候才压栈
        stack.push(v.name);
    }
}
