package org.revo.Search.Impl;


import org.revo.Hoolder.Node;
import org.revo.Search.BaseSearch;

import java.util.Comparator;

public class Uninformed implements BaseSearch {
    @Override
    public Comparator<Node> getCmp() {
        return (o1, o2) -> Integer.valueOf(getTotalCost(o1)).compareTo(getTotalCost(o2));
    }

    @Override
    public int getTotalCost(Node node) {
        return getG(node);
    }
}

