package org.revo.Search;

import org.revo.Hoolder.Node;
import org.revo.Hoolder.Result;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Supplier;

public interface BaseSearch {

    default Result find(Supplier<Node> sroot, String s) {
        PriorityQueue<Node> queue = new PriorityQueue<>(this.getCmp());
        Result result = new Result();
        queue.add(sroot.get());
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            n.setVisited(true);
            result.addToPath(n);
            if (n.getValue().equals(s)) {
                return result.setOut(n);
            }
            for (Node v : n.getChiled().keySet()) {
                if (v.isVisited()) {
                    continue;
                }
                v.setParent(n);
                queue.add(v);
            }
        }
        return result;
    }

    Comparator<Node> getCmp();

    int getTotalCost(Node node);

    default int getG(Node sNode) {
        int cost = 0;
        while (sNode != null) {
            Integer integer = sNode.getChiled().get(sNode.getParent());
            if (integer != null) {
                cost += integer;
            }
            sNode = sNode.getParent();
        }
        return cost;
    }
}
