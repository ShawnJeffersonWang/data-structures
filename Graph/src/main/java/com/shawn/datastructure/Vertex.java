package com.shawn.datastructure;

import java.util.List;

/**
 * 顶点
 * 对顶点和边的抽象
 * 面向对象的思想，以后要求解图的相关问题，再添加更多的属性
 */
public class Vertex {

    String name;
    List<Edge> edges;

    // 是否被访问过
    boolean visited;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
