package com.shawn.datastructure;

/**
 * 边
 */
public class Edge {

    // 这条边对应的终点是哪个顶点
    Vertex linked;
    int weight;

    public Edge(Vertex linked) {
        this(linked, 1);
    }

    public Edge(Vertex linked, int weight) {
        this.linked = linked;
        this.weight = weight;
    }
}
