package com.shawn.datastructure;

import java.util.List;
import java.util.Objects;

/**
 * 顶点
 * 对顶点和边的抽象
 * 面向对象的思想，以后要求解图的相关问题，再添加更多的属性
 */
public class Vertex {

    String name;
    List<Edge> edges;

    // 是否被访问过，用在 BFS 和 DFS
    boolean visited;
    // 入度
    int inDegree;
    // 状态 0-未访问 1-访问中 2-访问过，用在拓扑排序
    int status;

    // 距离
    int dist = INF;
    static final Integer INF = Integer.MAX_VALUE;
    Vertex prev = null;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    @Override
//    public String toString() {
//        return this.name + "(" + this.dist + ")";
//    }

    @Override
    public String toString() {
        String n = name;
        if (visited) {
            n = "\u001B[31m" + name + "\u001B[0m";
        }
        return n + '(' + (dist == Integer.MAX_VALUE ? "∞" : String.valueOf(dist)) + ") <- " + (prev == null ? "null" : prev.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public static void main(String[] args) {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        a.edges = List.of(new Edge(b), new Edge(c));
        b.edges = List.of(new Edge(d));
        c.edges = List.of(new Edge(d));
        d.edges = List.of();
    }
}
