package org.revo.Hoolder;


import java.util.HashMap;
import java.util.Map;

public class Node {
    private int h = 0;
    private boolean visited = false;
    private String value;
    private Map<Node, Integer> Chiled = new HashMap<>();
    private Node parent;


    public Node(String value, int h) {
        this.value = value;
        this.h = h;
    }

    public void AddNode(Node node, int toCost) {
        Chiled.put(node, toCost);

    }

    public int getH() {
        return h;
    }


    public Node setVisited(boolean visited) {
        this.visited = visited;
        return this;
    }

    public boolean isVisited() {
        return visited;
    }

    public String getValue() {
        return value;
    }


    public Map<Node, Integer> getChiled() {
        return Chiled;
    }


    public void setParent(Node parent) {
//        if (this.parent == null)
        this.parent = parent;

    }

    public Node getParent() {
        return parent;
    }

}
